package com.sh.app.movie.service;

import com.sh.app.movie.entity.Movie;
import lombok.extern.slf4j.Slf4j;
import com.sh.app.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public List<Movie> findFirst5ByOrderByAdvanceReservation() {
        return movieRepository.findFirst5ByOrderByAdvanceReservation();
    }
}
