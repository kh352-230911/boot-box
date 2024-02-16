package com.sh.app.cinema.controller;

import com.sh.app.cinema.dto.CinemaDto;

import com.sh.app.cinema.service.CinemaService;
import com.sh.app.location.dto.LocationDto;
import com.sh.app.location.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/cinema")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private LocationService locationService;

    @GetMapping("/cinemaDetail.do")
    public String cinemaDetail(@RequestParam("id") Long id, Model model) {
        CinemaDto cinemaDto = cinemaService.getCinemaDetails(id);
        model.addAttribute("cinema", cinemaDto);
        return "cinema/cinemaDetail";
    }

//    @GetMapping("/cinemaList.do")
//    public void cinemaList(@PageableDefault(size = 15, page = 0) Pageable pageable, Model model) {
//        Page<CinemaDto> cinemaDtoPage = cinemaService.findAll(pageable);
//        model.addAttribute("cinemas", cinemaDtoPage.getContent());
//    }

    @GetMapping("/cinemaList.do")
    public String cinemaList(Model model) {
        List<LocationDto> locationsWithCinemas = locationService.findAllLocationsWithCinemas();
        model.addAttribute("locations", locationsWithCinemas);
        return "cinema/cinemaList"; // 해당하는 Thymeleaf 템플릿 이름
    }

}
