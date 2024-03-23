package com.sh.app;

import com.sh.app.movie.dto.MovieDetailDto;
import com.sh.app.movie.service.MovieService;
import com.sh.app.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@Slf4j
public class HomeController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/")
    public String home(@RequestParam(value = "search", required = false) String search, Model model) {
        List<MovieDetailDto> movieDetailDtos;
        if(search == null) {
            movieDetailDtos = movieService.findFirst6ByOrderByRankAsc();
            log.debug("movieDetailDtos = {}", movieDetailDtos);
        }
        else {
            movieDetailDtos = movieService.findByTitleContaining(search);
            model.addAttribute("search", search);
            log.debug("movieDetailDtos = {}", movieDetailDtos);

        }
        model.addAttribute("movies", movieDetailDtos);
        return "index";
    }



}
