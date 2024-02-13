package com.sh.app;

import com.sh.app.movie.entity.Movie;
import com.sh.app.movie.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class HomeController {
    @Autowired
    MovieService movieService;

    @GetMapping("/")
    public String home(String search, Model model) {
        if(search == null) {
            List<Movie> movies = movieService.findFirst5ByOrderByAdvanceReservation();
            log.debug("movies = {}", movies);

            model.addAttribute("movies", movies);
        }
        else {
            List<Movie> movies = movieService.findByTitleContaining(search);
            log.debug("movies = {}", movies);

            model.addAttribute("movies", movies);
        }
        return "index";
    }

}
