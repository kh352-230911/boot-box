package com.sh.app.genre.serviece;

import com.sh.app.genre.entity.Genre;
import com.sh.app.genre.repository.GenreRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class GenreServiece {
    @Autowired
    private GenreRepository genreRepository;

    public Genre findByGenreId(Long genreId) {
        Genre genre = genreRepository.findByGenreId(genreId)
                .orElseThrow(() -> new EntityNotFoundException("Genre not found for ID: " + genreId));
        System.out.println(genre);

        return genre;
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }
}
