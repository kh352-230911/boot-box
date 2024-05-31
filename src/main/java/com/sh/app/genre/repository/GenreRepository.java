package com.sh.app.genre.repository;

import com.sh.app.genre.entity.Genre;
import com.sh.app.memberLikeGenre.dto.MemberLikeGenreListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByGenreId(Long genreId);

    Optional<Genre> findByGenreName(String normalizedGenreName);

    List<Genre> findByIdIn(ArrayList<Long> longs);

    boolean existsByGenreName(String genreName);



}
