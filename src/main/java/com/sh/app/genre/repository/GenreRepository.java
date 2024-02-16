package com.sh.app.genre.repository;

import com.sh.app.genre.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Set;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
