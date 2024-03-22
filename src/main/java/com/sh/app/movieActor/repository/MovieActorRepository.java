package com.sh.app.movieActor.repository;

import com.sh.app.movieActor.entity.MovieActor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieActorRepository extends JpaRepository<MovieActor, Long> {
}
