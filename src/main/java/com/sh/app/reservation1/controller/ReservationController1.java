package com.sh.app.reservation1.controller;
import com.sh.app.location.dto.LocationDto;
import com.sh.app.location.service.LocationService;
import com.sh.app.movie.entity.Movie;
import com.sh.app.movie.service.MovieService;
import com.sh.app.reservation1.service.ReservationService;
import com.sh.app.schedule.dto.ScheduleDto;
import com.sh.app.schedule.entity.Schedule;
import com.sh.app.schedule.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

/**
 * 0206
 * 예매 프론트단 부터 구현해보기
 * 예매화면은 사용자가 여러 루트로 접근할 수 있음.
 * 1.헤더의 예매 선택 : 오늘날짜 기준으로만 체크 - 영화,극장,상영관,시간대선택은 없음.
 * 2.특정 영화 상세페이지 및 예매버튼 클릭 : 오늘날짜 체크 - 영화만 선택
 *
 */
@Controller
@Slf4j
@RequestMapping("/reservation1")
/**
 * @RequestMapping은 클라이언트이 요청(url)에 맞는 클래스나 메서드를 연결시켜주는 어노테이션이다.
 * 이 어노테이션은 그 위치에 따라 의미가 다르다. 클래스 레벨 : 공통 주소 / 메서드 레벨에서 get/post 분류
 */
public class ReservationController1 {
    // 의존 주입 영역
    @Autowired
    private MovieService movieService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    LocationService locationService;

    @Autowired
    ScheduleService scheduleService;



    //첫 예매 페이지 진입 시 날짜(로컬)
    @GetMapping("/reservationBooking3.do")
    public void reservationMain(Model model)
    {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        List<String> dateList = new ArrayList<>();
        List<String> dayOfWeekList = new ArrayList<>();

        for (int i = 0; i < 21; i++) {
            LocalDate date = currentDate.plusDays(i);
            dateList.add(date.format(formatter));
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            dayOfWeekList.add(dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN)); //영문 요일을 한글요일[축약버전]으로
        }

        //0214 db 조회하여 현재 table에 있는 모든 영화 가져오기.
        List<Movie> movies;
        movies = movieService.findAll();
        log.debug("movies = {}", movies);

        //0214 db 조회하여 지역정보 가져오기
        List<LocationDto> locationsWithCinemas = locationService.findAllLocationsWithCinemas();
//        model.addAttribute("locations", locationsWithCinemas);

        //0215 db 조회하여 상영정보 가져오기
       // List<ScheduleDto> scheduleDtos = scheduleService.findAll();


        //0214 db조회 영화 결과값+ 날짜값 + 지역 결과값 묶어서 model값을 보내주고싶을때
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("movies", movies); //영화정보
        dataMap.put("dateList", dateList); // 날짜 [로컬에서 계산함]
        dataMap.put("dayOfWeekList", dayOfWeekList); //요일 [로컬에서 계산함]
        dataMap.put("locations",locationsWithCinemas); //지역정보



        // Model에 데이터 저장
        model.addAttribute("dataMap", dataMap);

    }
    @GetMapping("/reservationBooking4.do")
    public void reservationMain2(Model model)
    {

    }
}
