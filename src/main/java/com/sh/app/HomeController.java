package com.sh.app;

import com.sh.app.movie.dto.MovieListDto;
import com.sh.app.movie.entity.Movie;
import com.sh.app.movie.service.MovieService;
import com.sh.app.review.entity.Review;
import com.sh.app.review.repository.ReviewRepository;
import com.sh.app.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
public class HomeController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/")
    public String home(String search, Model model) {
        List<MovieListDto> movieListDtos;
        if(search == null) {
            movieListDtos = movieService.findFirst5ByOrderByAdvanceReservationDesc();
//            movieListDtos = movieService.findFirst5ByOrderByAdvanceReservation();
        }
        else {
            movieListDtos = movieService.findByTitleContaining(search);
        }
        log.debug("movies = {}", movieListDtos);
        model.addAttribute("movies", movieListDtos);
        return "index";
    }

}
