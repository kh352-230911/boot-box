package com.sh.app.movie.repository;

import com.sh.app.genre.entity.Genre;
import com.sh.app.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitleContaining(String title);
}
