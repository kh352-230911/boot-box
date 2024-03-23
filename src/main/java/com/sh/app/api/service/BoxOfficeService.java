package com.sh.app.api.service;

import com.sh.app.actor.dto.ActorInfoDto;
import com.sh.app.actor.entity.Actor;
import com.sh.app.actor.repository.ActorRepository;
import com.sh.app.director.dto.DirectorInfoDto;
import com.sh.app.director.entity.Director;
import com.sh.app.director.repository.DirectorRepository;
import com.sh.app.genre.entity.Genre;
import com.sh.app.genre.repository.GenreRepository;
import com.sh.app.movie.dto.*;
import com.sh.app.movie.entity.Movie;
import com.sh.app.movie.entity.Status;
import com.sh.app.movie.repository.MovieRepository;
import com.sh.app.movieActor.entity.MovieActor;
import com.sh.app.movieActor.repository.MovieActorRepository;
import com.sh.app.movieDirector.entity.MovieDirector;
import com.sh.app.movieDirector.repository.MovieDirectorRepository;
import com.sh.app.movieGenre.entity.MovieGenre;
import com.sh.app.movieGenre.repository.MovieGenreRepository;
import com.sh.app.util.GenreNormalization;
import com.sh.app.vod.dto.VodInfoDto;
import com.sh.app.vod.entity.Vod;
import com.sh.app.vod.repository.VodRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class BoxOfficeService {
    private static final String BOX_OFFICE_URL = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";

    private static final String KMDB_URL = "https://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp";

    private static final String YOUTUBE_URL = "https://www.googleapis.com/youtube/v3/search";

    @Value("${api_kmdb_key}")
    private String kmdbApiKey;

    @Value("${api_youtube_key}")
    private String youtubeApiKey;

    @Value("${api_kobis_key}")
    private String kobisApiKey;

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private RestTemplate restTemplateCustom;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MovieGenreRepository movieGenreRepository;

    @Autowired
    private VodRepository vodRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private MovieActorRepository movieActorRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private MovieDirectorRepository movieDirectorRepository;

    public void fetchAndStoreBoxOffice() {
        fetchAndStoreBoxOfficeData();
    }
    private String normalizeTitle(String title) {
        // 공백 제거
        return title.replaceAll("\\s+", "");
    }

    private void fetchAndStoreBoxOfficeData() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = yesterday.format(formatter);

        String url = UriComponentsBuilder
                .fromHttpUrl(BOX_OFFICE_URL)
                .queryParam("key", kobisApiKey)
                .queryParam("targetDt", formattedDate)
                .toUriString();

        BoxOfficeResponse boxOfficeResponse = restTemplate.getForObject(url, BoxOfficeResponse.class);
        if (boxOfficeResponse != null && boxOfficeResponse.getDailyBoxOfficeList() != null) {
            DailyBoxOfficeList dailyBoxOfficeList = boxOfficeResponse.getDailyBoxOfficeList();
            if (dailyBoxOfficeList != null && dailyBoxOfficeList.getBoxOfficeInfoDtos() != null) {
                for (BoxOfficeInfoDto boxOfficeInfoDto : dailyBoxOfficeList.getBoxOfficeInfoDtos()) {
                    String normalizedTitle = normalizeTitle(boxOfficeInfoDto.getTitle());
                    Optional<Movie> existingMovie = movieRepository.findByNormalizedTitle(normalizedTitle);
                    if (!existingMovie.isPresent()) {
                        Movie movie = convertToBoxOffice(boxOfficeInfoDto);

                        // 날짜 형식 포멧팅(yyyy-MM-dd -> yyyyMMdd)
                        String formattedReleaseDate = movie.getReleaseDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

                        // kobis 박스오피스 영화 제목과 포멧팅한 개봉일로 kmdb 영화 정보 조회
                        KmdbMovieInfoDto kmdbMovieInfoDto = fetchAndStoreKmdbData(boxOfficeInfoDto.getTitle(), formattedReleaseDate);

                        if (kmdbMovieInfoDto != null) {
                            // 상영시간
                            movie.setRuntime(kmdbMovieInfoDto.getRuntime() != null ? kmdbMovieInfoDto.getRuntime() : 0);

                            // 관람 등급
                            movie.setFilmRatings(kmdbMovieInfoDto.getFilmRatings() != null && !kmdbMovieInfoDto.getFilmRatings().isEmpty()
                                    ? kmdbMovieInfoDto.getFilmRatings() : "정보 없음");

                            // 첫 번째 포스터
                            String firstUrl = kmdbMovieInfoDto.getPosterUrl().split("\\|")[0];
                            movie.setPosterUrl(firstUrl);

                            // 첫 번째 줄거리(한국어)
                            String firstOverview = "";
                            if (kmdbMovieInfoDto.getOverview() != null && !kmdbMovieInfoDto.getOverview().getPlots().isEmpty()) {
                                firstOverview = kmdbMovieInfoDto.getOverview().getPlots().get(0).getPlotText();
                            }
                            movie.setOverview(firstOverview);

                            movie.setStatus(Status.box_office);
                            movie.setVoteAverage(0.0);

                            Movie savedMovie = movieRepository.save(movie);

                            // 예고편 처리
                            if (kmdbMovieInfoDto.getVods() != null) {
                                List<VodInfoDto> vodInfoDtos = kmdbMovieInfoDto.getVods().getVodInfoDtos();
                                if (vodInfoDtos == null || vodInfoDtos.isEmpty() || vodInfoDtos.stream().allMatch(v -> v.getVodUrl().isEmpty())) {
                                    String vodUrl = fetchVodUrlFromYoutube("영화 " + movie.getTitle() + " 메인 예고편");
                                    Vod vod = Vod.builder()
                                            .vodName(movie.getTitle() + " 메인 예고편")
                                            .vodUrl(vodUrl)
                                            .type("trailer")
                                            .movie(savedMovie)
                                            .build();
                                    movie.getVods().add(vod);

                                    vodRepository.save(vod);
                                }
                                else {
                                    vodInfoDtos.forEach(vodDto -> {
                                        Vod vod = Vod.builder()
                                                .vodName(vodDto.getVodName())
                                                .vodUrl(vodDto.getVodUrl().replace("trailerPlayPop?pFileNm=", "play/"))
                                                .type("trailer")
                                                .movie(savedMovie)
                                                .build();
                                        movie.getVods().add(vod);

                                        vodRepository.save(vod);
                                    });
                                }
                            }

                            // 장르 정보 처리
                            if (kmdbMovieInfoDto.getGenre() != null) {
                                String[] splitGenre = kmdbMovieInfoDto.getGenre().split(",");
                                for (String genreName : splitGenre) {
                                    String normalizedGenreName = GenreNormalization.normalizeGenreName(genreName.trim());
                                    Genre genre = genreRepository.findByGenreName(normalizedGenreName).orElseGet(() -> {
                                        Genre newGenre = Genre.builder()
                                                .genreId(null)
                                                .genreName(normalizedGenreName)
                                                .build();
                                        return genreRepository.save(newGenre);
                                    });
                                    MovieGenre movieGenre = MovieGenre.builder()
                                            .movie(savedMovie)
                                            .genre(genre)
                                            .build();
                                    movieGenreRepository.save(movieGenre);
                                }
                            }

                            // 배우 정보 처리
                            if (kmdbMovieInfoDto.getActors() != null) {
                                List<ActorInfoDto> actorInfoDtos = kmdbMovieInfoDto.getActors().getActorInfoDtos();
                                if (actorInfoDtos != null) {
                                    actorInfoDtos.stream().limit(5).forEach(actorDto -> {
                                        // 영화 정보에 있는 배우 이름으로 조회
                                        Actor actor = actorRepository.findByActorName(actorDto.getActorName())
                                                .orElseGet(() -> {
                                                    Actor newActor = Actor.builder()
                                                            .actorId(actorDto.getActorId())
                                                            .actorName(actorDto.getActorName())
                                                            .build();
                                                    return actorRepository.save(newActor);
                                                });

                                        // 영화 배우 브릿지 데이터 저장
                                        MovieActor movieActor = MovieActor.builder()
                                                .movie(savedMovie)
                                                .actor(actor)
                                                .build(); // 새 BoxMovieActor 객체 생성
                                        movieActorRepository.save(movieActor); // BoxMovieActor 저장
                                    });
                                }
                            }

                            // 감독 정보 처리
                            if (kmdbMovieInfoDto.getDirectors() != null) {
                                List<DirectorInfoDto> directorInfoDtos = kmdbMovieInfoDto.getDirectors().getDirectorInfoDtos();
                                if (directorInfoDtos != null) {
                                    directorInfoDtos.forEach(directorDto -> {
                                        // 영화 정보에 있는 감독 이름으로 조회
                                        Director director = directorRepository.findByDirectorName(directorDto.getDirectorName())
                                                .orElseGet(() -> {
                                                    Director newDirector = Director.builder()
                                                            .directorId(directorDto.getDirectorId())
                                                            .directorName(directorDto.getDirectorName())
                                                            .build();
                                                    return directorRepository.save(newDirector);
                                                });

                                        // 영화 감독 브릿지 데이터 저장
                                        MovieDirector movieDirector = MovieDirector.builder()
                                                .movie(savedMovie)
                                                .director(director)
                                                .build();
                                        movieDirectorRepository.save(movieDirector);
                                    });
                                }
                            }
                        }
                    }
                    else {
                        Movie existingTotalMovie = existingMovie.get();
                        existingTotalMovie.setRank(boxOfficeInfoDto.getRank()); // 랭킹 정보 업데이트
                        movieRepository.save(existingTotalMovie);
                    }
                }
            }
        }
    }

    private Movie convertToBoxOffice(BoxOfficeInfoDto boxOfficeInfoDto) {
        Movie movie = modelMapper.map(boxOfficeInfoDto, Movie.class);
        movie.setNormalizedTitle(normalizeTitle(boxOfficeInfoDto.getTitle()));
        return movie;
    }

    public KmdbMovieInfoDto fetchAndStoreKmdbData(String title, String formattedReleaseDate) {
        URI url = UriComponentsBuilder.fromHttpUrl(KMDB_URL)
                .queryParam("collection", "kmdb_new2")
                .queryParam("detail", "Y")
                .queryParam("title", title)
                .queryParam("releaseDts", formattedReleaseDate)
                .queryParam("ServiceKey", kmdbApiKey)
                .build()
                .encode()
                .toUri();

        KmdbResponse kmdbResponse = restTemplateCustom.getForObject(url, KmdbResponse.class);
        if (kmdbResponse != null && kmdbResponse.getResults() != null) {
            for (Result result : kmdbResponse.getResults()) {
                if (result != null && result.getKmdbMovieInfoDtos() != null) {
                    for (KmdbMovieInfoDto kmdbMovieInfoDto : result.getKmdbMovieInfoDtos()) {
                        return kmdbMovieInfoDto;
                    }
                }
            }
        }
        return null;
    }

    public String fetchVodUrlFromYoutube(String query) {
        // YouTube 검색 API URL을 구성합니다.
        URI youtubeSearchUrl = UriComponentsBuilder
                .fromHttpUrl(YOUTUBE_URL)
                .queryParam("part", "snippet")
                .queryParam("q", query)
                .queryParam("maxResults", 5)
                .queryParam("type", "video")
                .queryParam("key", youtubeApiKey)
                .build()
                .encode()
                .toUri();

        // YouTube API 호출 및 응답 수신
        String response = restTemplate.getForObject(youtubeSearchUrl, String.class);

        JSONObject jsonResponse = new JSONObject(response);
        JSONArray items = jsonResponse.getJSONArray("items");

        if (items.length() > 0) {
            JSONObject firstItem = items.getJSONObject(0);
            JSONObject id = firstItem.getJSONObject("id");
            String videoId = id.getString("videoId");
//            log.debug("videoId = {}", videoId);
//            return "https://www.youtube.com/embed/" + videoId;
            return videoId;
        } else {
            return "No results found";
        }
    }
}
