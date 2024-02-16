package com.sh.app.movie.service;

import com.sh.app.movie.dto.MovieDetailDto;
import com.sh.app.movie.dto.MovieListDto;
import com.sh.app.movie.entity.Movie;
import com.sh.app.movie.repository.MovieRepository;
import com.sh.app.review.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import java.util.stream.Collectors;

@Service
@Transactional
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ReviewRepository reviewRepository;

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

    public List<MovieListDto> findByTitleContaining(String search) {
        return movieRepository.findByTitleContaining(search)
                .stream().map((movie) -> convertToMovieListDto2(movie))
                .collect(Collectors.toList());
    }

    private MovieListDto convertToMovieListDto2(Movie movie) {
        MovieListDto movieListDto = modelMapper.map(movie, MovieListDto.class);
        Double avgReviewScore = reviewRepository.getAverageRatingByMovieId(movie.getId());
        movieListDto.setAvgReviewScore(avgReviewScore);
        movieListDto.setSearchResult(true);
        return movieListDto;
    }

    //    public List<MovieListDto> findFirst5ByOrderByAdvanceReservation() {
//        return movieRepository.findFirst5ByOrderByAdvanceReservationByDesc()
//                .stream().map((movie) -> convertToMovieListDto(movie))
//                .collect(Collectors.toList());
//    }
    public List<MovieListDto> findFirst5ByOrderByAdvanceReservationDesc() {
        return movieRepository.findFirst5ByOrderByAdvanceReservationDesc()
                .stream().map((movie) -> convertToMovieListDto(movie))
                .collect(Collectors.toList());
    }

    private MovieListDto convertToMovieListDto(Movie movie) {
        MovieListDto movieListDto = modelMapper.map(movie, MovieListDto.class);
        Double avgReviewScore = reviewRepository.getAverageRatingByMovieId(movie.getId());
        movieListDto.setAvgReviewScore(avgReviewScore);
        return movieListDto;
    }
}
