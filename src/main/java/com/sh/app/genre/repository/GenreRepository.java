package com.sh.app.genre.repository;

import com.sh.app.genre.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByGenreId(Long genreId);

    Optional<Genre> findByName(String normalizedGenreName);

    List<Genre> findByIdIn(ArrayList<Long> longs);
}
