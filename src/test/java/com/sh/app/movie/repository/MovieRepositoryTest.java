package com.sh.app.movie.repository;

import com.sh.app.genre.entity.Genre;
import com.sh.app.genre.repository.GenreRepository;
import com.sh.app.movie.entity.Movie;
import com.sh.app.movie.entity.Rating;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MovieRepositoryTest {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    GenreRepository genreRepository;

    @DisplayName("영화 전체 조회")
    @Test
    void test1() {
        // given
        insertMovieData();

        // when
        List<Movie> movies = movieRepository.findAll();
        System.out.println(movies);

        // then
        assertThat(movies)
                .isNotEmpty()
                .hasSize(movies.size())
                .containsOnly(
                        movies.get(0),
                        movies.get(1)
                );
    }

    public void insertMovieData() {
        List<Movie> movies = Arrays.asList(
                Movie.builder().title("웡카").fileRatings(Rating.G).releaseDate("2024.01.31").runningTime(116)
                        .trailer("https://www.kmdb.or.kr/trailer/trailerPlayPop?pFileNm=MK060560_P02.mp4")
                        .poster("http://file.koreafilm.or.kr/thm/02/99/18/30/tn_DPF028589.jpg")
                        .director("폴 킹").actor("티모시 샬라메").summary("세상에서 가장 달콤한 여정 좋은 일은 모두 꿈에서부터 시작된다!")
                        .advanceReservation(98).build(),
                Movie.builder().title("시민덕희").fileRatings(Rating.PG).releaseDate("2024.01.24").runningTime(114)
                        .trailer("https://www.kmdb.or.kr/trailer/trailerPlayPop?pFileNm=MK060515_P02.mp4")
                        .poster("http://file.koreafilm.or.kr/thm/02/99/18/28/tn_DPK021526.jpg")
                        .director("박영주").actor("라미란").summary("내 돈을 사기 친 그 놈이 구조 요청을 해왔다!")
                        .advanceReservation(99).build(),
                Movie.builder().title("외계인2").fileRatings(Rating.G).releaseDate("2024.01.10").runningTime(122)
                        .trailer("https://tv.naver.com/v/44600469")
                        .poster("http://file.koreafilm.or.kr/thm/02/99/18/28/tn_DPK021526.jpg")
                        .director("류준열").actor("최동훈").summary("반드시 돌아가야한다. 모두를 지키기 위해!")
                        .advanceReservation(97).build(),
                Movie.builder().title("서울의봄").fileRatings(Rating.G).releaseDate("2023.11.22").runningTime(141)
                        .trailer("https://www.kmdb.or.kr/trailer/trailerPlayPop?pFileNm=MK060515_P02.mp4")
                        .poster("http://file.koreafilm.or.kr/thm/02/99/18/28/tn_DPK021526.jpg")
                        .director("김성수").actor("황정민").summary("1979년 12월 12일, 수도 서울 군사반란 발생 그날, 대한민국의 운명이 바뀌었다!")
                        .advanceReservation(96).build(),
                Movie.builder().title("위시").fileRatings(Rating.G).releaseDate("2024.01.03").runningTime(95)
                        .trailer("https://www.kmdb.or.kr/trailer/trailerPlayPop?pFileNm=MK060515_P02.mp4")
                        .poster("http://file.koreafilm.or.kr/thm/02/99/18/28/tn_DPK021526.jpg")
                        .director("크리스벅").actor("아사이나").summary("디즈니 100주년 기념작!")
                        .advanceReservation(92).build(),
                Movie.builder().title("추락의 해부").fileRatings(Rating.PG).releaseDate("2024.01.31").runningTime(152)
                        .trailer("https://www.kmdb.or.kr/trailer/trailerPlayPop?pFileNm=MK060515_P02.mp4")
                        .poster("http://file.koreafilm.or.kr/thm/02/99/18/28/tn_DPK021526.jpg")
                        .director("쥐스틴").actor("산드라").summary("1남편의 추락사로 한순간에 유력한 용의자로 지목된 유명 작가 ‘산드라’!")
                        .advanceReservation(94).build()
        );
        movies.forEach(movieRepository::save);
    }

    @DisplayName("영화 등록")
    @Test
    void test2() {
        Movie movie = Movie.builder()
                .title("외계인")
                .poster("resources/static/images/외계인2부.jpg")
                .trailer("https://tv.naver.com/v/44600469")
                .releaseDate("2024.01.10")
                .runningTime(122)
                .summary("반드시 돌아가야한다. 모두를 지키기 위해!")
                .actor("류준열").director("최동훈")
                .advanceReservation(100)
                .fileRatings(Rating.G)
                .build();

        movie = movieRepository.save(movie);
        assertThat(movie.getId()).isNotNull().isNotZero();


    }
    @DisplayName("영화 수정")
    @Test
    void test3() {
        final Movie movie = Movie.builder()
                .title("외계인")
                .poster("resources/static/images/외계인2부.jpg")
                .releaseDate("2024.01.10")
                .runningTime(122)
                .advanceReservation(100)
                .fileRatings(Rating.G)
                .build();

        movieRepository.save(movie);

        String newTitle = ("외계인2");
        int newRunningTime = 123;
        Rating newRating = Rating.PG;
        movie.setTitle(newTitle);
        movie.setRunningTime(newRunningTime);
        movie.setFileRatings(newRating);
        movieRepository.save(movie);

        Optional<Movie> optMovie = movieRepository.findById(movie.getId());
        Movie movie2 = optMovie.orElse(null);

        assertThat(movie2).isNotNull()
                .satisfies((_movie) -> {
                    assertThat(_movie.getId()).isEqualTo(movie.getId());
                    assertThat(_movie.getTitle()).isEqualTo(newTitle);
                    assertThat(_movie.getRunningTime()).isEqualTo(newRunningTime);
                    assertThat(_movie.getFileRatings()).isEqualTo(newRating);
                });
    }
    @DisplayName("영화 삭제")
    @Test
    void test4() {
        final Movie movie = Movie.builder()
                .title("외계인")
                .poster("resources/static/images/외계인2부.jpg")
                .releaseDate("2024.01.10")
                .runningTime(122)
                .advanceReservation(100)
                .fileRatings(Rating.G)
                .build();

        movieRepository.save(movie);

        movieRepository.deleteById(movie.getId());

        Optional<Movie> optMovie = movieRepository.findById(movie.getId());
        Movie movie2 = optMovie.orElse(null);
        assertThat(movie2).isNull();
    }

    @DisplayName("무비차트 예매율순으로 5개조회")
    @Test
    void test5() {
        insertMovieData();
        List<Movie> movies = movieRepository.findFirst5ByOrderByAdvanceReservation();
        System.out.println(movies);

        assertThat(movies)
                .hasSize(5)
                .allSatisfy(movie -> {
                    assertThat(movie.getAdvanceReservation()).isNotNull();
                });
    }
    @DisplayName("장르에따른 영화목록들 조회")
    @Test
    void test6() {
        Genre genre = Genre.builder()
                .genreList("드라마")
                .build();
        Genre genre1 = Genre.builder()
                .genreList("판타지")
                .build();
        genreRepository.save(genre);
        genreRepository.save(genre1);

        Movie movie = Movie.builder().title("웡카").fileRatings(Rating.G).releaseDate("2024.01.31").runningTime(116)
                        .trailer("https://www.kmdb.or.kr/trailer/trailerPlayPop?pFileNm=MK060560_P02.mp4")
                        .poster("http://file.koreafilm.or.kr/thm/02/99/18/30/tn_DPF028589.jpg")
                        .director("폴 킹").actor("티모시 샬라메").summary("세상에서 가장 달콤한 여정 좋은 일은 모두 꿈에서부터 시작된다!")
                        .advanceReservation(98).build();
        Movie movie1 = Movie.builder().title("시민덕희").fileRatings(Rating.PG).releaseDate("2024.01.24").runningTime(114)
                        .trailer("https://www.kmdb.or.kr/trailer/trailerPlayPop?pFileNm=MK060515_P02.mp4")
                        .poster("http://file.koreafilm.or.kr/thm/02/99/18/28/tn_DPK021526.jpg")
                        .director("박영주").actor("라미란").summary("내 돈을 사기 친 그 놈이 구조 요청을 해왔다!")
                        .advanceReservation(99).build();


        System.out.println(genre);
        System.out.println(genre1);
        movie.addMovieGenre(genre);
        movie1.addMovieGenre(genre1);

        movieRepository.save(movie);
        movieRepository.save(movie1);
        System.out.println(movie);
        System.out.println(movie1);

        List<Movie> movies = movieRepository.findByGenreList(genre.getGenreList());
        System.out.println(movies);

    }

}
