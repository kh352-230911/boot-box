package com.sh.app.movie.repository;

import com.sh.app.genre.entity.Genre;
import com.sh.app.genre.repository.GenreRepository;
import com.sh.app.movie.entity.Movie;
import com.sh.app.movie.entity.Rating;
import com.sh.app.moviegenre.entity.MovieGenre;
import com.sh.app.moviegenre.repository.MovieGenreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
    @Autowired
    MovieGenreRepository movieGenreRepository;

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

    @DisplayName("영화 검색 조회")
    @Test
    void test2() {
        // given
        insertMovieData();
        String title = "시민";

        // when
        List<Movie> movies = movieRepository.findByTitleContaining(title);
        System.out.println(movies);

        // then
        assertThat(movies)
                .isNotEmpty()
                .hasSize(movies.size())
                .allSatisfy((movie -> {
                    assertThat(movie.getTitle().contains(title));
                }));
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
                        .advanceReservation(99).build()
        );
        movies.forEach(movieRepository::save);

        List<MovieGenre> movieGenres = movieGenreRepository.findAll();
        System.out.println(movieGenres);
    }
}
