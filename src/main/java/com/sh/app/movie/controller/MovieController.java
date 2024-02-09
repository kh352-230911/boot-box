package com.sh.app.movie.controller;

import com.sh.app.movie.entity.Movie;
import com.sh.app.movie.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/movie")
public class MovieController {
    // 의존 주입 영역
    @Autowired
    MovieService movieService;

    // ------------------------------------------ //

    // 초임
    @GetMapping("/movieDetail.do")
    public void movieDetail() {}

    // ------------------------------------------ //

    // 정호

    @GetMapping("/movieList.do")
    public void movieList() {}

    // ------------------------------------------ //
}