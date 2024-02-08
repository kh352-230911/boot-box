package com.sh.app.cinema.controller;

import com.sh.app.cinema.service.CinemaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/cinema")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @GetMapping("/cinemaDetail.do")
    public void cinemaDetail() {

    }

    @GetMapping("/cinemaList.do")
    public void cinemaList() {

    }

}
