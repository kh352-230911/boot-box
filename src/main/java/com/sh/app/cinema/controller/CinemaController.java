package com.sh.app.cinema.controller;

import com.sh.app.cinema.dto.CinemaDetailsDTO;
import com.sh.app.cinema.service.CinemaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/cinema")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @GetMapping("/cinemaDetail.do/{id}")
    public void getCinemaDetails(@PathVariable("id") Long id, Model model) {
        CinemaDetailsDTO cinemaDetails = cinemaService.getCinemaDetails(id);

        model.addAttribute("cinemaDetails", cinemaDetails);
        log.debug("cinemaDetails = {}", cinemaDetails);

    }

    @GetMapping("/cinemaList.do")
    public void cinemaList() {

    }

}
