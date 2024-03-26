//package com.sh.app.movieData.controller;
//
//import com.sh.app.movieData.dto.MovieDataDetailDto;
//import com.sh.app.movieData.entity.MovieData;
//import com.sh.app.movieData.service.MovieDataService;
//import com.sh.app.review.service.ReviewService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
//@Controller
//@Slf4j
//@RequestMapping("/movie")
//public class MovieDataController {
//    // 의존 주입 영역
//    @Autowired
//    private MovieDataService movieDataService;
//    @Autowired
//    private ReviewService reviewService;
//
//    // ------------------------------------------ //
//
//    // 초임
//    @GetMapping("/movieDetail.do")
//    public void movieDetail(Long id, Model model){
//        MovieDataDetailDto movieDataDetailDto = movieDataService.findById(id);
////        log.debug("movieDetailDto = {}", movieDetailDto);
//        model.addAttribute("movie", movieDataDetailDto);
//    }
//
//
//
//    // ------------------------------------------ //
//
//    // 정호
//    @GetMapping("/movieList.do")
//    public void movieList(String genre, Model model) {
//
//        List<MovieData> movieData;
//        if (genre == null) {
//            movieData = movieDataService.findAll();
//        }
//        else {
//            movieData = movieDataService.findByGenreList(genre);
//        }
//        model.addAttribute("movies", movieData);
//    }
//
//    // ------------------------------------------ //
//}
