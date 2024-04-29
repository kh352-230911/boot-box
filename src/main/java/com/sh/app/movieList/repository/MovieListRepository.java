package com.sh.app.movieList.repository;

import com.sh.app.movieGenre.entity.MovieGenre;
import com.sh.app.movieList.entity.MovieList;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface MovieListRepository extends JpaRepository<MovieList, Long> {

    @Transactional
    void deleteMovieByMovieIdAndCinemaId(Long cinemaId, Long movieId);


    @Query("SELECT ml FROM MovieList ml JOIN ml.movie m WHERE ml.cinema.id = :cinemaId")
    List<MovieList> findMoviesByCinemaId(Long cinemaId);
}
