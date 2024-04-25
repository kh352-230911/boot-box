package com.sh.app.movieList.repository;

import com.sh.app.movieGenre.entity.MovieGenre;
import com.sh.app.movieList.entity.MovieList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieListRepository extends JpaRepository<MovieList, Long> {
}
