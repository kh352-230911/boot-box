package com.sh.app.movie.controller;

import com.sh.app.movie.service.MovieService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/movie")
public class MovieDetailController {

    @Autowired
    MovieService movieService;

    @GetMapping("/movieDetail.do")
    public void movieDetail() {

    }
}
