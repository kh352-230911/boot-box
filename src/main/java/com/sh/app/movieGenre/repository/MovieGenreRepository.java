package com.sh.app.movieGenre.repository;

import com.sh.app.movieGenre.entity.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieGenreRepository extends JpaRepository<MovieGenre, Long> {
}
