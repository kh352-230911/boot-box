//package com.sh.app.movieData.repository;
//
//import com.sh.app.genre.repository.GenreRepository;
//import com.sh.app.genreData.entity.GenreData;
//import com.sh.app.genreData.repository.GenreDataRepository;
//import com.sh.app.movie.entity.Movie;
//import com.sh.app.movie.repository.MovieRepository;
//import com.sh.app.movieData.entity.MovieData;
//import com.sh.app.movieData.entity.Rating;
//import com.sh.app.review.repository.ReviewRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class MovieDataRepositoryTest {
//    @Autowired
//    private MovieRepository movieRepository;
//    @Autowired
//    private GenreRepository genreRepository;
//    @Autowired
//    private ReviewRepository reviewRepository;
//
//    @DisplayName("영화 전체 조회")
//    @Test
//    void test1() {
//        // given
//        insertMovieData();
//
//        // when
//        List<Movie> movieData = movieRepository.findAll();
//        System.out.println(movieData);
//
//        // then
//        assertThat(movieData)
//                .isNotEmpty()
//                .hasSize(movieData.size())
//                .containsOnly(
//                        movieData.get(0),
//                        movieData.get(1)
//                );
//    }
//
//    public void insertMovieData() {
//        List<Movie> movieData = Arrays.asList(
//                Movie.builder().title("웡카").filmRatings(Rating.ALL).releaseDate("2024.01.31").runningTime(116)
//                        .trailer("https://www.kmdb.or.kr/trailer/trailerPlayPop?pFileNm=MK060560_P02.mp4")
//                        .poster("http://file.koreafilm.or.kr/thm/02/99/18/30/tn_DPF028589.jpg")
//                        .director("폴 킹").actor("티모시 샬라메").summary("세상에서 가장 달콤한 여정 좋은 일은 모두 꿈에서부터 시작된다!")
//                        .advanceReservation(98).build(),
//                Movie.builder().title("시민덕희").filmRatings(Rating.FIFTEEN).releaseDate("2024.01.24").runningTime(114)
//                        .trailer("https://www.kmdb.or.kr/trailer/trailerPlayPop?pFileNm=MK060515_P02.mp4")
//                        .poster("http://file.koreafilm.or.kr/thm/02/99/18/28/tn_DPK021526.jpg")
//                        .director("박영주").actor("라미란").summary("내 돈을 사기 친 그 놈이 구조 요청을 해왔다!")
//                        .advanceReservation(99).build(),
//                Movie.builder().title("외계인2").filmRatings(Rating.ALL).releaseDate("2024.01.10").runningTime(122)
//                        .trailer("https://tv.naver.com/v/44600469")
//                        .poster("http://file.koreafilm.or.kr/thm/02/99/18/28/tn_DPK021526.jpg")
//                        .director("류준열").actor("최동훈").summary("반드시 돌아가야한다. 모두를 지키기 위해!")
//                        .advanceReservation(97).build(),
//                Movie.builder().title("서울의봄").filmRatings(Rating.TWELVE).releaseDate("2023.11.22").runningTime(141)
//                        .trailer("https://www.kmdb.or.kr/trailer/trailerPlayPop?pFileNm=MK060515_P02.mp4")
//                        .poster("http://file.koreafilm.or.kr/thm/02/99/18/28/tn_DPK021526.jpg")
//                        .director("김성수").actor("황정민").summary("1979년 12월 12일, 수도 서울 군사반란 발생 그날, 대한민국의 운명이 바뀌었다!")
//                        .advanceReservation(96).build(),
//                Movie.builder().title("위시").filmRatings(Rating.ALL).releaseDate("2024.01.03").runningTime(95)
//                        .trailer("https://www.kmdb.or.kr/trailer/trailerPlayPop?pFileNm=MK060515_P02.mp4")
//                        .poster("http://file.koreafilm.or.kr/thm/02/99/18/28/tn_DPK021526.jpg")
//                        .director("크리스벅").actor("아사이나").summary("디즈니 100주년 기념작!")
//                        .advanceReservation(92).build(),
//                Movie.builder().title("추락의 해부").filmRatings(Rating.FIFTEEN).releaseDate("2024.01.31").runningTime(152)
//                        .trailer("https://www.kmdb.or.kr/trailer/trailerPlayPop?pFileNm=MK060515_P02.mp4")
//                        .poster("http://file.koreafilm.or.kr/thm/02/99/18/28/tn_DPK021526.jpg")
//                        .director("쥐스틴").actor("산드라").summary("1남편의 추락사로 한순간에 유력한 용의자로 지목된 유명 작가 ‘산드라’!")
//                        .advanceReservation(94).build()
//        );
//        movieData.forEach(movieRepository::save);
//
//    }
//
//    @DisplayName("영화 등록")
//    @Test
//    void test2() {
//        Movie movie = Movie.builder()
//                .title("외계인")
//                .posterUrl("resources/static/images/외계인2부.jpg")
//                .trailer("https://tv.naver.com/v/44600469")
//                .releaseDate("2024.01.10")
//                .runningTime(122)
//                .summary("반드시 돌아가야한다. 모두를 지키기 위해!")
//                .actor("류준열").director("최동훈")
//                .advanceReservation(100)
//                .filmRatings(Rating.ALL)
//                .build();
//
//        movie = movieRepository.save(movie);
//        assertThat(movie.getId()).isNotNull().isNotZero();
//    }
//
//    @DisplayName("영화 수정")
//    @Test
//    void test3() {
//        final MovieData movieData = MovieData.builder()
//                .title("외계인")
//                .poster("resources/static/images/외계인2부.jpg")
//                .releaseDate("2024.01.10")
//                .runningTime(122)
//                .advanceReservation(100)
//                .filmRatings(Rating.ALL)
//                .build();
//
//        movieDataRepository.save(movieData);
//
//        String newTitle = ("외계인2");
//        int newRunningTime = 123;
//        Rating newRating = Rating.ALL;
//        movieData.setTitle(newTitle);
//        movieData.setRunningTime(newRunningTime);
//        movieData.setFilmRatings(newRating);
//        movieDataRepository.save(movieData);
//
//        Optional<MovieData> optMovie = movieDataRepository.findById(movieData.getId());
//        MovieData movieData2 = optMovie.orElse(null);
//
//        assertThat(movieData2).isNotNull()
//                .satisfies((_movie) -> {
//                    assertThat(_movie.getId()).isEqualTo(movieData.getId());
//                    assertThat(_movie.getTitle()).isEqualTo(newTitle);
//                    assertThat(_movie.getRunningTime()).isEqualTo(newRunningTime);
//                    assertThat(_movie.getFilmRatings()).isEqualTo(newRating);
//                });
//    }
//
//    @DisplayName("영화 삭제")
//    @Test
//    void test4() {
//        final MovieData movieData = MovieData.builder()
//                .title("외계인")
//                .poster("resources/static/images/외계인2부.jpg")
//                .releaseDate("2024.01.10")
//                .runningTime(122)
//                .advanceReservation(100)
//                .filmRatings(Rating.ALL)
//                .build();
//
//        movieDataRepository.save(movieData);
//
//        movieDataRepository.deleteById(movieData.getId());
//
//        Optional<MovieData> optMovie = movieDataRepository.findById(movieData.getId());
//        MovieData movieData2 = optMovie.orElse(null);
//        assertThat(movieData2).isNull();
//    }
//
//    @DisplayName("무비차트 예매율순으로 5개조회")
//    @Test
//    void test5() {
//        insertMovieData();
////        List<Movie> movies = movieRepository.findFirst5ByOrderByAdvanceReservation();
//        List<MovieData> movieData = movieDataRepository.findFirst5ByOrderByAdvanceReservationDesc();
//        System.out.println(movieData);
//
//        assertThat(movieData)
//                .hasSize(5)
//                .allSatisfy(movie -> {
//                    assertThat(movie.getAdvanceReservation()).isNotNull();
//                });
//    }
//
//    @DisplayName("장르에따른 영화목록들 조회")
//    @Test
//    void test6() {
//        GenreData genreData = GenreData.builder()
//                .genreList("드라마")
//                .build();
//        GenreData genreData1 = GenreData.builder()
//                .genreList("판타지")
//                .build();
//        genreDataRepository.save(genreData);
//        genreDataRepository.save(genreData1);
//
//        MovieData movieData = MovieData.builder().title("웡카").filmRatings(Rating.ALL).releaseDate("2024.01.31").runningTime(116)
//                .trailer("https://www.kmdb.or.kr/trailer/trailerPlayPop?pFileNm=MK060560_P02.mp4")
//                .poster("http://file.koreafilm.or.kr/thm/02/99/18/30/tn_DPF028589.jpg")
//                .director("폴 킹").actor("티모시 샬라메").summary("세상에서 가장 달콤한 여정 좋은 일은 모두 꿈에서부터 시작된다!")
//                .advanceReservation(98).build();
//        MovieData movieData1 = MovieData.builder().title("시민덕희").filmRatings(Rating.FIFTEEN).releaseDate("2024.01.24").runningTime(114)
//                .trailer("https://www.kmdb.or.kr/trailer/trailerPlayPop?pFileNm=MK060515_P02.mp4")
//                .poster("http://file.koreafilm.or.kr/thm/02/99/18/28/tn_DPK021526.jpg")
//                .director("박영주").actor("라미란").summary("내 돈을 사기 친 그 놈이 구조 요청을 해왔다!")
//                .advanceReservation(99).build();
//
//
//        System.out.println(genreData);
//        System.out.println(genreData1);
//        movieData.addMovieGenre(genreData);
//        movieData1.addMovieGenre(genreData1);
//
//        movieDataRepository.save(movieData);
//        movieDataRepository.save(movieData1);
//        System.out.println(movieData);
//        System.out.println(movieData1);
//
//        List<MovieData> movieData = movieDataRepository.findByGenreList(genreData.getGenreList());
//        System.out.println(movieData);
//
//    }
//
//    @DisplayName("영화 검색 조회")
//    @Test
//    void test7() {
//         //given
//
//        insertMovieData();
//        String title = "시민";
//
//        // when
//        List<MovieData> movieData = movieDataRepository.findByTitleContaining(title);
//        System.out.println(movieData);
//
//        // then
//        assertThat(movieData)
//                .isNotEmpty()
//                .hasSize(movieData.size())
//                .allSatisfy((movie -> {
//                    assertThat(movie.getTitle().contains(title));
//                }));
//    }
//
//}
//
