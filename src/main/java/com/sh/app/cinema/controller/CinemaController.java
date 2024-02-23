package com.sh.app.cinema.controller;

import com.sh.app.auth.vo.MemberDetails;
import com.sh.app.cinema.dto.CinemaDto;

import com.sh.app.cinema.service.CinemaService;
import com.sh.app.location.dto.LocationDto;
import com.sh.app.location.service.LocationService;
import com.sh.app.memberLikeCinema.dto.MemberLikeCinemaListDto;
import com.sh.app.memberLikeCinema.serviece.MemberLikeCinemaService;
import com.sh.app.movie.dto.MovieListDto;
import com.sh.app.movie.service.MovieService;
import com.sh.app.schedule.dto.IScheduleInfoDto;
import com.sh.app.schedule.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    private MemberLikeCinemaService memberLikeCinemaService;

    @GetMapping("/cinemaList.do")
    public String cinemaList(Model model, @AuthenticationPrincipal MemberDetails memberDetails) {

        List<LocationDto> locationsWithCinemas = locationService.findAllLocationsWithCinemas();
        List<MemberLikeCinemaListDto> memberLikeCinemaListDtos = memberLikeCinemaService.findByMemberId(memberDetails.getMember().getId());

        log.debug("memberLIkeCinemaListDtos = {}", memberLikeCinemaListDtos);

        model.addAttribute("locations", locationsWithCinemas);
        model.addAttribute("memberLikeCinemas", memberLikeCinemaListDtos);



        return "cinema/cinemaList"; // 해당하는 Thymeleaf 템플릿 이름
    }

    @GetMapping("/cinemaDetail.do")
    public String cinemaDetail(@RequestParam("id") Long id, Model model) {
        CinemaDto cinemaDto = cinemaService.getCinemaDetails(id);
        List<MovieListDto> currentMovies = movieService.getCurrentMovies(); // 현재 상영 중인 영화 목록 가져오기
        model.addAttribute("cinema", cinemaDto);
        model.addAttribute("currentMovies", currentMovies); // 모델에 추가
        log.debug("cinemaDto = {}", cinemaDto);
        log.debug("currentMovies = {}", currentMovies);
        return "cinema/cinemaDetail";
    }

    @GetMapping("/scheduleByDate")
    public ResponseEntity<?> getScheduleByDate(@RequestParam("id") Long id,
                                               @RequestParam("selectedDate")
                                               @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate selectedDate) {

        log.debug("id = {}", id); // 극장 ID
        log.debug("selectedDate = {}", selectedDate); // 선택된날짜
        List<IScheduleInfoDto> scheduleDetails = scheduleService.findScheduleDetailsByDateAndCinemaId(id, selectedDate);
        log.debug("scheduleDetails = {}", scheduleDetails);

        // 로직을 추가하여 scheduleDetails에서 필요한 JSON 구조로 변환
        List<Map<String, Object>> organizedSchedules = scheduleService.organizeSchedules(scheduleDetails);
        log.debug("organizedSchedules = {}", organizedSchedules);

        // JSON 구조로 클라이언트에 반환
        return ResponseEntity.ok(organizedSchedules);
    }

}
