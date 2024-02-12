package com.sh.app.movie.service;

import com.sh.app.movie.dto.MovieDetailDto;
import com.sh.app.movie.entity.Movie;
import lombok.extern.slf4j.Slf4j;
import com.sh.app.movie.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Movie> findFirst5ByOrderByAdvanceReservation() {
        return movieRepository.findFirst5ByOrderByAdvanceReservation();
    }

    public MovieDetailDto findById(Long id) {
        return movieRepository.findById(id)
                .map((movie) -> convertToMovieDetailDto(movie))
                .orElseThrow();
    }

    private MovieDetailDto convertToMovieDetailDto(Movie movie) {
        MovieDetailDto movieDetailDto = modelMapper.map(movie, MovieDetailDto.class);
        return movieDetailDto;
    }


    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public List<Movie> findByGenreList(String genre) {
        return movieRepository.findByGenreList(genre);
    }

    public List<Movie> findByTitleContaining(String search) {
        return movieRepository.findByTitleContaining(search);
    }
}
