package com.sh.app.movieDirector.repository;

import com.sh.app.movieDirector.entity.MovieDirector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDirectorRepository extends JpaRepository<MovieDirector, Long> {
}
