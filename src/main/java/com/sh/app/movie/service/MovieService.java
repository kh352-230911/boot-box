package com.sh.app.movie.service;

import com.sh.app.actor.dto.ActorDetailDto;
import com.sh.app.actor.dto.ActorDto;
import com.sh.app.actor.dto.ActorInfoDto;
import com.sh.app.actor.dto.ActorResponse;
import com.sh.app.actor.entity.Actor;
import com.sh.app.actor.repository.ActorRepository;
import com.sh.app.director.dto.DirectorDetailDto;
import com.sh.app.director.dto.DirectorDto;
import com.sh.app.director.dto.DirectorInfoDto;
import com.sh.app.director.dto.DirectorResponse;
import com.sh.app.director.entity.Director;
import com.sh.app.genre.dto.GenreDetailDto;
import com.sh.app.genre.dto.GenreDto;
import com.sh.app.genre.dto.GenreResponse;
import com.sh.app.genre.entity.Genre;
import com.sh.app.genre.repository.GenreRepository;
import com.sh.app.movie.dto.*;
import com.sh.app.movie.entity.Movie;
import com.sh.app.movie.entity.Status;
import com.sh.app.movie.repository.MovieRepository;
import com.sh.app.movieActor.entity.MovieActor;
import com.sh.app.movieActor.repository.MovieActorRepository;
import com.sh.app.movieDirector.entity.MovieDirector;
import com.sh.app.director.repository.DirectorRepository;
import com.sh.app.movieDirector.repository.MovieDirectorRepository;
import com.sh.app.movieGenre.entity.MovieGenre;
import com.sh.app.movieGenre.repository.MovieGenreRepository;
import com.sh.app.util.GenreNormalization;
import com.sh.app.vod.dto.VodDetailDto;
import com.sh.app.vod.dto.VodDto;
import com.sh.app.vod.dto.VodInfoDto;
import com.sh.app.vod.dto.VodsResponse;
import com.sh.app.vod.entity.Vod;
import com.sh.app.vod.repository.VodRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class MovieService {
    private static final String BOX_OFFICE_URL = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";

    private static final String KMDB_URL = "https://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp";

    private static final String GENRE_URL = "https://api.themoviedb.org/3/genre/movie/list";

    private static final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing";

    private static final String VODS_URL = "https://api.themoviedb.org/3/movie/%d/videos?language=ko-KR&api_key=%s";

    private static final String YOUTUBE_URL = "https://www.googleapis.com/youtube/v3/search";

    private static final String CREDITS_URL = "https://api.themoviedb.org/3/movie/%d/credits?language=ko-KR&api_key=%s";

    @Value("${api_kmdb_key}")
    private String kmdbApiKey;

    @Value("${api_tmdb_key}")
    private String tmdbApiKey;

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

    public void scheduledCallApi() {
        movieGenreRepository.deleteAll();
        genreRepository.deleteAll();
        vodRepository.deleteAll();
        movieActorRepository.deleteAll();
        actorRepository.deleteAll();
        movieDirectorRepository.deleteAll();
        directorRepository.deleteAll();
        movieRepository.deleteAll();
        fetchAndStoreMovie();
    }

    private String normalizeTitle(String title) {
        // 공백 제거
        return title.replaceAll("\\s+", "");
    }

    public void fetchAndStoreMovie() {
        fetchAndStoreGenres();
        fetchAndStoreAllMovies();
        fetchAndStoreBoxOfficeData();
    }

    private void fetchAndStoreGenres() {
        genreRepository.deleteAll();
        String url = UriComponentsBuilder
                .fromHttpUrl(GENRE_URL)
                .queryParam("api_key", tmdbApiKey)
                .queryParam("language", "ko-KR")
                .toUriString();

        GenreResponse genreResponse = restTemplate.getForObject(url, GenreResponse.class);
//        log.debug("genreResponse = {}", genreResponse);
        if (genreResponse != null) {
            try {
                for (GenreDto genreDto : genreResponse.getGenreDtos()) {
                    Genre genre = convertToGenre(genreDto);
                    genreRepository.save(genre);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Genre convertToGenre(GenreDto genreDto) {
        return modelMapper.map(genreDto, Genre.class);
    }


    public void fetchAndStoreAllMovies() {
        int totalPages = fetchTotalPages();
        for (int page = 1; page <= totalPages; page++) {
            fetchAndStoreMoviesByPage(page);
        }
    }

    private int fetchTotalPages() {
        String url = buildUrl(1); // 첫 번째 페이지 URL 생성
        MovieResponse
                response = restTemplate.getForObject(url, MovieResponse.class);
        return response != null ? response.getTotalPages() : 0;
    }

    private void fetchAndStoreMoviesByPage(int page) {
        String url = buildUrl(page);
        MovieResponse movieResponse = restTemplate.getForObject(url, MovieResponse.class);
        if (movieResponse != null) {
            for (TmdbMovieInfoDto tmdbMovieInfoDto : movieResponse.getTmdbMovieInfoDtos()) {
                String normalizedTitle = normalizeTitle(tmdbMovieInfoDto.getTitle());
                Optional<Movie> existingMovie = movieRepository.findByNormalizedTitle(normalizedTitle);
                if (!existingMovie.isPresent()) {
                    Movie movie = convertToMovie(tmdbMovieInfoDto);

                    String formattedReleaseDate = movie.getReleaseDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                    KmdbMovieInfoDto kmdbMovieInfoDto = fetchAndStoreKmdbData(movie.getTitle(), formattedReleaseDate);

                    // movieInfoDto가 null이 아닌 경우 각 필드 검사
                    if (kmdbMovieInfoDto != null) {
                        // rating 필드 검사: null이면 "정보 없음"을, 아니면 해당 값을 사용
                        //                    log.debug("movieInfoDto.getRating() = {}", movieInfoDto.getRating());
                        movie.setFilmRatings(kmdbMovieInfoDto.getFilmRatings() != null && !kmdbMovieInfoDto.getFilmRatings().isEmpty()
                                ? kmdbMovieInfoDto.getFilmRatings() : "정보 없음");
                        // runtime 필드 검사: null이면 0을, 아니면 해당 값을 사용
                        movie.setRuntime(kmdbMovieInfoDto.getRuntime() != null ? kmdbMovieInfoDto.getRuntime() : 0);
                    } else {
                        // movieInfoDto 자체가 null인 경우 기본값 설정
                        movie.setFilmRatings("정보 없음");
                        movie.setRuntime(0);
                    }

                    movie.setRank(null);
                    movie.setStatus(Status.current_movie);

                    Movie savedMovie = movieRepository.saveAndFlush(movie); // 영화 정보 저장

                    fetchAndStoreMovieVods(savedMovie.getId());

                    fetchAndStoreMovieCredits(savedMovie.getId());

                    // 장르 ID 리스트를 반복하면서 MovieGenre 인스턴스를 생성하고 저장
                    for (Long genreId : tmdbMovieInfoDto.getGenreIds()) {
                        // 각 장르 ID에 해당하는 Genre 엔티티를 찾음
                        Genre genre = genreRepository.findByGenreId(genreId)
                                .orElseThrow(() -> new EntityNotFoundException("Genre not found for ID: " + genreId));

                        // MovieGenre 인스턴스 생성 및 Movie와 Genre 연결
                        MovieGenre movieGenre = MovieGenre.builder()
                                .movie(savedMovie)
                                .genre(genre)
                                .build();
                        savedMovie.addMovieGenre(movieGenre);
                        // MovieGenre 인스턴스 저장
                        movieGenreRepository.save(movieGenre);
                    }
                }
            }
        }
    }

    private void fetchAndStoreMovieCredits(Long id) {
        String url = String.format(CREDITS_URL, id, tmdbApiKey);
        ActorResponse actorResponse = restTemplate.getForObject(url, ActorResponse.class);

        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));

        if (actorResponse != null) {
            for (ActorDto actorDto : actorResponse.getActorDtos()) {
                int order = actorDto.getOrder();
                if (order <= 4) {
                    Actor actor = actorRepository.findByActorName(actorDto.getActorName())
                            .orElseGet(() -> {
                                Actor newActor = Actor.builder()
                                        .actorId(actorDto.getActorId())
                                        .actorName(actorDto.getActorName())
                                        .build();
                                return actorRepository.save(newActor);
                            });

                    MovieActor movieActor = MovieActor.builder()
                            .movie(movie)
                            .actor(actor)
                            .build();
                    movie.addMovieActor(movieActor);
                    movieActorRepository.save(movieActor);
                }
            }
        }

        DirectorResponse directorResponse = restTemplate.getForObject(url, DirectorResponse.class);

        if (directorResponse != null) {
            for (DirectorDto directorDto : directorResponse.getDirectorDtos()) {
                if ("Director".equals(directorDto.getJob())) {
                    Director director = directorRepository.findByDirectorName(directorDto.getDirectorName())
                            .orElseGet(() -> {
                                Director newDirector = Director.builder()
                                        .directorId(directorDto.getDirectorId())
                                        .directorName(directorDto.getDirectorName())
                                        .build();
                                return directorRepository.save(newDirector);
                            });

                    MovieDirector movieDirector = MovieDirector.builder()
                            .movie(movie)
                            .director(director)
                            .build();
                    movie.addMovieDirector(movieDirector);
                    movieDirectorRepository.save(movieDirector);
                }
            }
        }
    }

    private void fetchAndStoreMovieVods(Long id) {
        String url = String.format(VODS_URL, id, tmdbApiKey);
        VodsResponse vodsResponse = restTemplate.getForObject(url, VodsResponse.class);

        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));

        if (vodsResponse != null && vodsResponse.getVodDtos().isEmpty()){
            String vodUrl = fetchVodUrlFromYoutube("영화 " + movie.getTitle() + "메인 예고편");
            if (vodUrl != null) {
                VodDto vodDto = VodDto.builder()
                        .vodName(movie.getTitle() + " 메인 예고편")
                        .vodUrl(vodUrl)
                        .type("trailer")
                        .build();
                Vod vod = convertToVod(vodDto);
                vod.setMovie(movie);
                movie.getVods().add(vod);
                vodRepository.save(vod);

            }
        }
        else if (vodsResponse != null) {
            for (VodDto vodDto : vodsResponse.getVodDtos()) {
                Vod vod = convertToVod(vodDto);
                vod.setMovie(movie);
                movie.getVods().add(vod);
                vodRepository.save(vod);
            }
        }
    }

    private Vod convertToVod(VodDto vodDto) {
        return modelMapper.map(vodDto, Vod.class);
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

    private Movie convertToMovie(TmdbMovieInfoDto tmdbMovieInfoDto) {
        Movie movie = modelMapper.map(tmdbMovieInfoDto, Movie.class);
        movie.setNormalizedTitle(normalizeTitle(tmdbMovieInfoDto.getTitle()));
        return movie;
    }

    private String buildUrl(int page) {
        return UriComponentsBuilder
                .fromHttpUrl(NOW_PLAYING_URL)
                .queryParam("api_key", tmdbApiKey)
                .queryParam("language", "ko-KR")
                .queryParam("page", page)
                .queryParam("region", "KR")
                .toUriString();
    }

    public void fetchAndStoreBoxOfficeData() {
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

                            Movie savedMovie = movieRepository.saveAndFlush(movie);

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
                                } else {
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
                                    savedMovie.addMovieGenre(movieGenre);
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
                                        movie.addMovieActor(movieActor);
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
                                        movie.addMovieDirector(movieDirector);
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


    public MovieDetailDto findById(Long id) {
        return movieRepository.findById(id)
                .map((movie) -> convertToMovieDetailDto(movie))
                .orElseThrow();
    }

    private MovieDetailDto convertToMovieDetailDto(Movie movie) {
        MovieDetailDto movieDetailDto = modelMapper.map(movie, MovieDetailDto.class);
        movieDetailDto.setSearchResult(true);
        return movieDetailDto;
    }

    public List<MovieDetailDto> findAllByOrderByRankAsc() {
        // 영화 목록을 먼저 가져옵니다.
        List<Movie> movies = movieRepository.findAllByOrderByRankAsc();
        List<MovieDetailDto> movieDetailDtos = new ArrayList<>();

//        // 모든 영화에 대한 배우 ID를 한 번에 수집하기
//        Set<Long> allActorIds = new HashSet<>();
//        movies.forEach(movie -> allActorIds.addAll(
//                movie.getMovieActors().stream()
//                        .map(movieActor -> movieActor.getActor().getId())
//                        .collect(Collectors.toSet())
//        ));
//
//        // 배우 ID 목록을 사용하여 배우 정보를 한 번의 쿼리로 가져오기
//        List<Actor> allActors = actorRepository.findByIdIn(new ArrayList<>(allActorIds));
//        Map<Long, ActorDetailDto> actorInfoMap = allActors.stream()
//                .map(actor -> modelMapper.map(actor, ActorDetailDto.class))
//                .collect(Collectors.toMap(ActorDetailDto::getId, Function.identity()));
//
//        // 모든 영화에 대한 감독 ID를 한 번에 수집하기
//        Set<Long> allDirectorIds = new HashSet<>();
//        movies.forEach(movie -> allDirectorIds.addAll(
//                movie.getMovieDirectors().stream()
//                        .map(movieDirector -> movieDirector.getDirector().getId())
//                        .collect(Collectors.toSet())
//        ));
//
//        // 감독 ID 목록을 사용하여 감독 정보를 한 번의 쿼리로 가져오기
//        List<Director> allDirectors = directorRepository.findByIdIn(new ArrayList<>(allDirectorIds));
//        Map<Long, DirectorDetailDto> directorInfoMap = allDirectors.stream()
//                .map(director -> modelMapper.map(director, DirectorDetailDto.class))
//                .collect(Collectors.toMap(DirectorDetailDto::getId, Function.identity()));
//
//        // 모든 영화에 대한 장르 ID를 한 번에 수집하기
//        Set<Long> allGenreIds = new HashSet<>();
//        movies.forEach(movie -> allGenreIds.addAll(
//                movie.getMovieGenres().stream()
//                        .map(movieGenre -> movieGenre.getGenre().getId())
//                        .collect(Collectors.toSet())
//        ));
//
//        // 장르 ID 목록을 사용하여 장르 정보를 한 번의 쿼리로 가져오기
//        List<Genre> allGenres = genreRepository.findByIdIn(new ArrayList<>(allGenreIds));
//        Map<Long, GenreDetailDto> genreInfoMap = allGenres.stream()
//                .map(genre -> modelMapper.map(genre, GenreDetailDto.class))
//                .collect(Collectors.toMap(GenreDetailDto::getId, Function.identity()));
//
//        // 영화 목록을 반복하면서 DTO를 생성합니다.
        for (Movie movie : movies) {
//            // BoxVideoInfoDto 변환
//            List<VodDetailDto> vodDetailDtos = movie.getVods().stream()
//                    .map(vod -> modelMapper.map(vod, VodDetailDto.class))
//                    .collect(Collectors.toList());
//
//            // 영화별로 연관된 장르 정보를 매핑
//            List<GenreDetailDto> genreDetailDtos = movie.getMovieGenres().stream()
//                    .map(MovieGenre::getGenre)
//                    .map(genre -> genreInfoMap.get(genre.getId()))
//                    .collect(Collectors.toList());
//
//            // 영화별로 연관된 배우 정보를 매핑
//            List<ActorDetailDto> actorDetailDtos = movie.getMovieActors().stream()
//                    .map(MovieActor::getActor)
//                    .map(actor -> actorInfoMap.get(actor.getId()))
//                    .collect(Collectors.toList());
//
//            // 영화별로 연관된 감독 정보를 매핑
//            List<DirectorDetailDto> directorDetailDtos = movie.getMovieDirectors().stream()
//                    .map(MovieDirector::getDirector)
//                    .map(director -> directorInfoMap.get(director.getId()))
//                    .collect(Collectors.toList());

            // DTO 빌더를 사용하여 BoxMovieInfoDto를 생성합니다.
            MovieDetailDto movieDetailDto = convertToMovieList(movie);
//            MovieDetailDto movieDetailDto = MovieDetailDto.builder()
//                    .rank(movie.getRank())
//                    .title(movie.getTitle())
//                    .releaseDate(movie.getReleaseDate())
//                    .filmRatings(movie.getFilmRatings())
//                    .runtime(movie.getRuntime())
//                    .overview(movie.getOverview())
//                    .voteAverage(movie.getVoteAverage())
//                    .posterUrl(movie.getPosterUrl())
//                    .build();
//
            movieDetailDtos.add(movieDetailDto);
        }
        return movieDetailDtos;
    }

    private MovieDetailDto convertToMovieList(Movie movie) {
        MovieDetailDto movieDetailDto = modelMapper.map(movie, MovieDetailDto.class);
        movieDetailDto.setDDay(calculateDday(movie.getReleaseDate()));
        return movieDetailDto;
    }

    private String calculateDday(LocalDate releaseDate) {
        LocalDate today = LocalDate.now();
        if (releaseDate.isAfter(today)) {
            long daysBetween = ChronoUnit.DAYS.between(today, releaseDate);
            return "D-" + daysBetween;
        } else {
            // 개봉일이 현재 날짜보다 이전이거나 같은 경우
            return ""; // 빈 문자열 반환 또는 "개봉됨", "N/A" 등 원하는 값 반환
        }

    }

//    public List<MovieDetailDto> findByGenreName(String genre) {
//        return movieRepository.findByGenreName(genre).stream()
//                .map((movie) -> convertToMovieDetailDto(movie))
//                .collect(Collectors.toList());
//    }

    public List<MovieListDto> getCurrentMovies() {
        return movieRepository.findAll().stream() // 현재는 findAll을 사용했지만, 실제로는 현재 상영 중인 영화를 필터링하는 로직
                .map(movie -> {
                    MovieListDto dto = new MovieListDto();
                    dto.setId(movie.getId());
                    dto.setTitle(movie.getTitle());
                    dto.setPosterUrl(movie.getPosterUrl());
                    // 필요한 정보만 설정
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public List<MovieShortDto> shotMovie() {
        return movieRepository.findAllByOrderByTitleAsc()
                .stream().map((movie) -> entityToDto(movie))
                .collect(Collectors.toList());
    }

    private MovieShortDto entityToDto(Movie movie) {
        return modelMapper.map(movie, MovieShortDto.class);
    }

//    public List<MovieDetailDto> findFirst6ByOrderByRankAsc() {
//        return movieRepository.findFirst6ByOrderByRankAsc()
//                .stream().map((movie) -> convertToMovieDetailDto(movie))
//                .collect(Collectors.toList());
//    }
//
//    public List<MovieDetailDto> findByTitleContaining(String title) {
//        return movieRepository.findByTitleContaining(title)
//                .stream().map((movie) -> convertToMovieDetailDto(movie))
//                .collect(Collectors.toList());
//    }
public List<MovieDetailDto> findFirst6ByOrderByRankAsc() {
    List<Movie> movies = movieRepository.findFirst6ByOrderByRankAsc();
    List<MovieDetailDto> movieDetailDtos = new ArrayList<>();

    for (Movie movie : movies) {
        List<VodDetailDto> vodDetailDtos = movie.getVods().stream()
                .map(vod -> modelMapper.map(vod, VodDetailDto.class))
                .collect(Collectors.toList());

        MovieDetailDto dto = convertToMovieList(movie);
        dto.setVodDetailDtos(vodDetailDtos);

        movieDetailDtos.add(dto);
    }
    return movieDetailDtos;
}

    public List<MovieDetailDto> findByTitleContaining(String title) {
        // 입력된 제목의 길이를 체크하여 한 글자인 경우 빈 리스트를 반환
        if (title == null || title.trim().length() <= 1) {
            return Collections.emptyList();
        }

        List<Movie> movies = movieRepository.findByTitleContaining(title);
        List<MovieDetailDto> movieDetailDtos = new ArrayList<>();

        for (Movie movie : movies) {
            List<VodDetailDto> vodDetailDtos = movie.getVods().stream()
                    .map(vod -> modelMapper.map(vod, VodDetailDto.class))
                    .collect(Collectors.toList());

            MovieDetailDto dto = convertToMovieList(movie);
            dto.setVodDetailDtos(vodDetailDtos);

            movieDetailDtos.add(dto);
        }
        return movieDetailDtos;
    }

}
