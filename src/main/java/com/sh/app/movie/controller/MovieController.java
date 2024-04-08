package com.sh.app.movie.controller;

import com.sh.app.movie.dto.MovieDetailDto;
import com.sh.app.movie.service.MovieService;
import com.sh.app.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private ReviewService reviewService;

    // ---------------------------------------------- //
    // 초임
    @GetMapping("/movieDetail.do")
    public void movieDetail(Long id, Model model){
        MovieDetailDto movieDetailDto = movieService.findById(id);
        log.debug("movieDetailDto = {}", movieDetailDto);
        model.addAttribute("movie", movieDetailDto);
    }

    // ---------------------------------------------- //

    // 정호
    @GetMapping("/movieList.do")
    public void movieList(@RequestParam(value = "genre", required = false) String genre,
                          Model model) {
//        List<MovieDetailDto> movies;
//
//        if (genre == null || genre.isEmpty()) {
//            movies = movieService.findAllByOrderByRankAsc();
//        }
//        else {
//            movies = movieService.findByGenreName(genre);
//        }
//
//        log.debug("movies = {}", movies);
//
//        model.addAttribute("movies", movies);
    }

    @GetMapping("/moviePageList.do")
    @ResponseBody
    public ResponseEntity<?> moviePageList(@RequestParam(value = "genre", required = false) String genre,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<MovieDetailDto> moviePage;

        if (genre == null || genre.isEmpty()) {
            moviePage = movieService.findAllByOrderByRankAsc(page, size);
        }
        else {
            moviePage = movieService.findByGenreName(genre, page, size);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("content", moviePage.getContent());
        response.put("hasNext", moviePage.hasNext());
        response.put("currentPage", page);
        response.put("size", size);
        response.put("totalPages", moviePage.getTotalPages());

        log.debug("response = {}", response);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/preMovieList.do")
    public void preMovieList(@RequestParam(value = "genre", required = false) String genre, Model model) {
//        List<MovieDetailDto> movies;
//        if (genre == null || genre.isEmpty()) {
//            movies = movieService.findAllByReleaseDateAfterOrderByRankAsc();
//        }
//        else {
//            movies = movieService.findByGenresNameAndReleaseDateAfter(genre);
//        }
//        log.debug("movies = {}", movies);
//        model.addAttribute("genre", genre);
//        model.addAttribute("movies", movies);
    }

    @GetMapping("/preMoviePageList.do")
    @ResponseBody
    public ResponseEntity<?> preMoviePageList(@RequestParam(value = "genre", required = false) String genre,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "4") int size) {

        Page<MovieDetailDto> moviePage;

        if (genre == null || genre.isEmpty()) {
            moviePage = movieService.findAllByReleaseDateAfterOrderByRankAsc(page, size);
        }
        else {
            moviePage = movieService.findByGenresNameAndReleaseDateAfter(genre, page, size);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("content", moviePage.getContent());
        response.put("hasNext", moviePage.hasNext());
        response.put("currentPage", page);
        response.put("size", size);
        response.put("totalPages", moviePage.getTotalPages());

        log.debug("response = {}", response);

        return ResponseEntity.ok(response);
    }

    // ---------------------------------------------- //
}
