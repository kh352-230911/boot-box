package com.sh.app.api.service;

import com.sh.app.actor.dto.ActorDto;
import com.sh.app.actor.dto.ActorResponse;
import com.sh.app.actor.entity.Actor;
import com.sh.app.actor.repository.ActorRepository;
import com.sh.app.director.dto.DirectorDto;
import com.sh.app.director.dto.DirectorResponse;
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
import com.sh.app.vod.dto.VodDto;
import com.sh.app.vod.dto.VodsResponse;
import com.sh.app.vod.entity.Vod;
import com.sh.app.vod.repository.VodRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class CurrentMovieService {
    private static final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing";

    private static final String KMDB_URL = "https://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp";

    private static final String VODS_URL = "https://api.themoviedb.org/3/movie/%d/videos?language=ko-KR&api_key=%s";

    private static final String YOUTUBE_URL = "https://www.googleapis.com/youtube/v3/search";

    private static final String CREDITS_URL = "https://api.themoviedb.org/3/movie/%d/credits?language=ko-KR&api_key=%s";

    @Value("${api_kmdb_key}")
    private String kmdbApiKey;

    @Value("${api_tmdb_key}")
    private String tmdbApiKey;

    @Value("${api_youtube_key}")
    private String youtubeApiKey;

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


    private String normalizeTitle(String title) {
        // 공백 제거
        return title.replaceAll("\\s+", "");
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
        movieRepository.deleteAll();
        actorRepository.deleteAll();
        movieActorRepository.deleteAll();
        directorRepository.deleteAll();
        movieDirectorRepository.deleteAll();
        movieGenreRepository.deleteAll();
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

//                    Movie savedMovie = movieRepository.save(movie);
                    Movie savedMovie = saveAndFlushMovie(movie); // 영화 정보 저장

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

                    fetchAndStoreMovieVods(savedMovie.getId());

                    fetchAndStoreMovieCredits(savedMovie.getId());
                }
            }
        }
    }

    public Movie saveAndFlushMovie(Movie movie) {
        return movieRepository.saveAndFlush(movie); // 저장 후 즉시 커밋하여 ID 생성
    }

    private void fetchAndStoreMovieCredits(Long id) {
        String url = String.format(CREDITS_URL, id, tmdbApiKey);
        ActorResponse actorResponse = restTemplate.getForObject(url, ActorResponse.class);

        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
        log.debug("Credit = {}", movie);

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
        vodRepository.deleteAll();
        String url = String.format(VODS_URL, id, tmdbApiKey);
        VodsResponse vodsResponse = restTemplate.getForObject(url, VodsResponse.class);
//        log.debug("vodsResponse = {}", vodsResponse);

        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
//        log.debug("Vod = {}", movie);

//        if (vodsResponse != null && vodsResponse.getVodDtos().isEmpty()){
//            String vodUrl = fetchVodUrlFromYoutube("영화 " + movie.getTitle() + "메인 예고편");
//            if (vodUrl != null) {
//                VodDto vodDto = VodDto.builder()
//                        .vodName(movie.getTitle() + " 메인 예고편")
//                        .vodUrl(vodUrl)
//                        .type("trailer")
//                        .movie(movie)
//                        .build();
//                Vod vod = convertToVod(vodDto);
//                movie.getVods().add(vod);

//                vodRepository.save(vod);
//
//            }
//        }
//        else if (vodsResponse != null) {
            for (VodDto vodDto : vodsResponse.getVodDtos()) {
                Vod vod = convertToVod(vodDto);
                vod.setMovie(movie);
                movie.getVods().add(vod);

                vodRepository.save(vod);
            }
//        }
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
}
