package com.sh.app.movie.controller;

import com.sh.app.movie.dto.MovieDetailDto;
import com.sh.app.movie.entity.Movie;
import com.sh.app.movie.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/movie")
public class MovieController {
    // 의존 주입 영역
    @Autowired
    private MovieService movieService;

    // ------------------------------------------ //

    // 초임
    @GetMapping("/movieDetail.do")
    public void movieDetail(Long id, Model model) {
        MovieDetailDto movieDetailDto = movieService.findById(id);
        model.addAttribute("movie",movieDetailDto);
        log.debug("movieDetailDto = {}", movieDetailDto);
    }

    // ------------------------------------------ //

    // 정호
    @GetMapping("/movieList.do")
    public void movieList(String genre, Model model) {
        log.debug("genre = {}", genre);

        List<Movie> movies;
        if (genre == null) {
            movies = movieService.findAll();
        }
        else {
            movies = movieService.findByGenreList(genre);
        }
        log.debug("movies = {}", movies);
        model.addAttribute("movies", movies);
    }
    // ------------------------------------------ //
}
