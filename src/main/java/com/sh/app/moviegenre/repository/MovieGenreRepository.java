package com.sh.app.moviegenre.repository;

import com.sh.app.movie.entity.Movie;
import com.sh.app.moviegenre.entity.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieGenreRepository extends JpaRepository<MovieGenre, Long> {

}
