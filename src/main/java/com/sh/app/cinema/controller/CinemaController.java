package com.sh.app.cinema.controller;

import com.sh.app.cinema.dto.CinemaDetailDto;
import com.sh.app.cinema.dto.CinemaDto;

import com.sh.app.cinema.service.CinemaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/cinema")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @GetMapping("/cinemaDetail.do")
    public String cinemaDetail(@RequestParam("regionCinema") String regionCinema, Model model) {
        model.addAttribute("regionCinema", regionCinema); // 모델에 극장 지점명 추가
        // 추가적인 로직 (예: 해당 지점의 상영 정보 조회 등)
        return "cinema/cinemaDetail"; // Thymeleaf 템플릿 이름
    }

    @GetMapping("/cinemaList.do")
    public void cinemaList(@PageableDefault(size = 15, page = 0) Pageable pageable, Model model) {
        Page<CinemaDto> cinemaDtoPage = cinemaService.findAll(pageable);
        model.addAttribute("cinemas", cinemaDtoPage.getContent());
    }

}
