package com.sh.app.cinema.controller;

import com.sh.app.cinema.dto.CinemaDto;

import com.sh.app.cinema.service.CinemaService;
import com.sh.app.location.dto.LocationDto;
import com.sh.app.location.service.LocationService;
import com.sh.app.movie.dto.MovieListDto;
import com.sh.app.movie.service.MovieService;
import com.sh.app.reservation1.service.ReservationService;
import com.sh.app.schedule.dto.IScheduleInfoDto;
import com.sh.app.schedule.dto.ScheduleInfoDto;
import com.sh.app.schedule.dto.TheaterScheduleDto;
import com.sh.app.schedule.dto.TimeSlotDto;
import com.sh.app.schedule.service.ScheduleService;
import com.sh.app.seat.service.SeatService;
import com.sh.app.theater.service.TheaterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedHashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/cinema")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ScheduleService scheduleService;


    @GetMapping("/cinemaDetail.do")
    public String cinemaDetail(@RequestParam("id") Long id, Model model) {
        CinemaDto cinemaDto = cinemaService.getCinemaDetails(id);
        List<MovieListDto> currentMovies = movieService.getCurrentMovies(); // 현재 상영 중인 영화 목록 가져오기
        model.addAttribute("cinema", cinemaDto);
        model.addAttribute("currentMovies", currentMovies); // 모델에 추가
        return "cinema/cinemaDetail";
    }

    @GetMapping("/scheduleByDate")
    public String getScheduleByDate(@RequestParam("id") Long id,
                                    @RequestParam("selectedDate") @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate selectedDate,
                                    Model model) {
        log.debug("id = {}", id); // 극장 ID
        log.debug("selectedDate = {}", selectedDate); // 선택된날짜
        List<IScheduleInfoDto> scheduleDetails = scheduleService.findScheduleDetailsByDateAndCinemaId(id, selectedDate);

        model.addAttribute("scheduleDetails", scheduleDetails);
        log.debug("scheduleDetails = {}", scheduleDetails);

        return "cinema/cinemaDetail :: scheduleList"; // Thymeleaf내 fragment.scheduleList로 반환
    }

    @GetMapping("/cinemaList.do")
    public String cinemaList(Model model) {
        List<LocationDto> locationsWithCinemas = locationService.findAllLocationsWithCinemas();
        model.addAttribute("locations", locationsWithCinemas);
        return "cinema/cinemaList"; // 해당하는 Thymeleaf 템플릿 이름
    }

}
