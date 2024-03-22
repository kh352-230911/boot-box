//package com.sh.app.movieData.service;
//
//import com.sh.app.genreData.repository.GenreDataRepository;
//import com.sh.app.movieData.dto.*;
//import com.sh.app.movieData.entity.MovieData;
//import com.sh.app.movieData.repository.MovieDataRepository;
//import com.sh.app.review.repository.ReviewRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//@Slf4j
//public class MovieDataService {
//    @Autowired
//    private MovieDataRepository movieDataRepository;
//    @Autowired
//    private ModelMapper modelMapper;
//    @Autowired
//    private ReviewRepository reviewRepository;
//    @Autowired
//    private GenreDataRepository genreDataRepository;
//
//    public MovieDataDetailDto findById(Long id) {
//        return movieDataRepository.findById(id)
//                .map((movie) -> convertToMovieDetailDto(movie))
//                .orElseThrow();
//    }
//
//    private MovieDataDetailDto convertToMovieDetailDto(MovieData movieData) {
//        MovieDataDetailDto movieDataDetailDto = modelMapper.map(movieData, MovieDataDetailDto.class);
//        Double avgReviewScore = reviewRepository.getAverageRatingByMovieId(movieData.getId());
//        movieDataDetailDto.setAvgReviewScore(avgReviewScore);
//        return movieDataDetailDto;
//    }
//
//
//    public List<MovieData> findAll() {
//        return movieDataRepository.findAll();
//    }
//
//    public List<MovieData> findAllByOrderByTitleAsc() {
//        return movieDataRepository.findAllByOrderByTitleAsc();
//    }
//
//    public List<MovieData> findByGenreList(String genre) {
//        return movieDataRepository.findByGenreList(genre);
//    }
//
//    public List<MovieDataDetailDto> findByTitleContaining(String search) {
//        return movieDataRepository.findByTitleContaining(search)
//                .stream().map((movie) -> convertToMovieListDto2(movie))
//                .collect(Collectors.toList());
//    }
//
//
//    private MovieDataDetailDto convertToMovieListDto2(MovieData movieData) {
//        MovieDataDetailDto movieDataDetailDto = modelMapper.map(movieData, MovieDataDetailDto.class);
//        Double avgReviewScore = reviewRepository.getAverageRatingByMovieId(movieData.getId());
//        movieDataDetailDto.setAvgReviewScore(avgReviewScore);
//        movieDataDetailDto.setSearchResult(true);
//        return movieDataDetailDto;
//    }
//
//    public List<MovieDataDetailDto> findFirst5ByOrderByAdvanceReservationDesc() {
//        return movieDataRepository.findFirst5ByOrderByAdvanceReservationDesc()
//                .stream().map((movie) -> convertToMovieListDto(movie))
//                .collect(Collectors.toList());
//    }
//
//    private MovieDataDetailDto convertToMovieListDto(MovieData movieData) {
//        MovieDataDetailDto movieDataDetailDto = modelMapper.map(movieData, MovieDataDetailDto.class);
//        Double avgReviewScore = reviewRepository.getAverageRatingByMovieId(movieData.getId());
//        movieDataDetailDto.setAvgReviewScore(avgReviewScore);
//        return movieDataDetailDto;
//    }
//
//    public List<MovieDataListDto> getCurrentMovies() {
//        return movieDataRepository.findAll().stream() // 현재는 findAll을 사용했지만, 실제로는 현재 상영 중인 영화를 필터링하는 로직
//                .map(movie -> {
//                    MovieDataListDto dto = new MovieDataListDto();
//                    dto.setId(movie.getId());
//                    dto.setTitle(movie.getTitle());
//                    dto.setPoster(movie.getPoster());
//                    // 필요한 정보만 설정
//                    return dto;
//                })
//                .collect(Collectors.toList());
//    }
//
//    //0221 예매 첫 페이지에 출력해 줄 용도 dto [필요한 컬럼 4개뿐임]
//    public MovieDataShortDto entityToDto(MovieData movieData) {
//        MovieDataShortDto msDto = modelMapper.map(movieData, MovieDataShortDto.class);
//        return msDto;
//    }
//
//    // 다수의 엔티티를 DTO로 변환하는 메소드 findall하되 타이틀 오름차순 적용
//    public List<MovieDataShortDto> shotMovie() {
//        return movieDataRepository.findAllByOrderByTitleAsc()
//                .stream().map((movie) -> entityToDto(movie))
//                .collect(Collectors.toList());
//    }
//
//}
