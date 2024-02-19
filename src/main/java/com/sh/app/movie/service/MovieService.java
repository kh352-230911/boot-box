package com.sh.app.movie.service;

import com.sh.app.genre.repository.GenreRepository;
import com.sh.app.movie.dto.MovieDetailDto;
import com.sh.app.movie.dto.MovieListDto;
import com.sh.app.movie.entity.Movie;
import com.sh.app.movie.repository.MovieRepository;
import com.sh.app.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private GenreRepository genreRepository;

    public MovieDetailDto findById(Long id) {
        return movieRepository.findById(id)
                .map((movie) -> convertToMovieDetailDto(movie))
                .orElseThrow();

    }

    private MovieDetailDto convertToMovieDetailDto(Movie movie) {
        MovieDetailDto movieDetailDto = modelMapper.map(movie, MovieDetailDto.class);
        Double avgReviewScore = reviewRepository.getAverageRatingByMovieId(movie.getId());
        movieDetailDto.setAvgReviewScore(avgReviewScore);
        return movieDetailDto;
    }


    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public List<Movie> findAllByOrderByTitleAsc() {
        return movieRepository.findAllByOrderByTitleAsc();
    }

    public List<Movie> findByGenreList(String genre) {
        return movieRepository.findByGenreList(genre);
    }

    public List<MovieDetailDto> findByTitleContaining(String search) {
        return movieRepository.findByTitleContaining(search)
                .stream().map((movie) -> convertToMovieListDto2(movie))
                .collect(Collectors.toList());
    }


    private MovieDetailDto convertToMovieListDto2(Movie movie) {
        MovieDetailDto movieDetailDto = modelMapper.map(movie, MovieDetailDto.class);
        Double avgReviewScore = reviewRepository.getAverageRatingByMovieId(movie.getId());
        movieDetailDto.setAvgReviewScore(avgReviewScore);
        movieDetailDto.setSearchResult(true);
        return movieDetailDto;
    }

    public List<MovieDetailDto> findFirst5ByOrderByAdvanceReservationDesc() {
        return movieRepository.findFirst5ByOrderByAdvanceReservationDesc()
                .stream().map((movie) -> convertToMovieListDto(movie))
                .collect(Collectors.toList());

    }


    private MovieDetailDto convertToMovieListDto(Movie movie) {
        MovieDetailDto movieDetailDto = modelMapper.map(movie, MovieDetailDto.class);
        Double avgReviewScore = reviewRepository.getAverageRatingByMovieId(movie.getId());
        movieDetailDto.setAvgReviewScore(avgReviewScore);
        return movieDetailDto;
    }

    public List<MovieListDto> getCurrentMovies() {
        return movieRepository.findAll().stream() // 현재는 findAll을 사용했지만, 실제로는 현재 상영 중인 영화를 필터링하는 로직
                .map(movie -> {
                    MovieListDto dto = new MovieListDto();
                    dto.setId(movie.getId());
                    dto.setTitle(movie.getTitle());
                    dto.setPoster(movie.getPoster());
                    // 필요한 정보만 설정
                    return dto;
                })
                .collect(Collectors.toList());
    }

}
