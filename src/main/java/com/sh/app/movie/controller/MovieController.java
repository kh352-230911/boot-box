package com.sh.app.movie.controller;

import com.sh.app.movie.dto.MovieDetailDto;
import com.sh.app.movie.entity.Movie;
import com.sh.app.movie.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        log.debug("movieDetailDto = {}", movieDetailDto);

        model.addAttribute("movie", movieDetailDto);
    }

    // ------------------------------------------ //

    // 정호

    @GetMapping("/movieList.do")
    public void movieList(Model model) {
        List<Movie> movies = movieService.findAll();
        log.debug("movies = {}", movies);

        model.addAttribute("movies", movies);
    }

    // ------------------------------------------ //
}
