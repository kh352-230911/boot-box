package com.sh.app.reservation.controller;
import com.sh.app.auth.vo.MemberDetails;
import com.sh.app.location.dto.LocationDto;
import com.sh.app.location.service.LocationService;
import com.sh.app.member.entity.Member;
import com.sh.app.movie.entity.Movie;
import com.sh.app.movie.service.MovieService;
import com.sh.app.reservation.service.ReservationService;
import com.sh.app.schedule.dto.ScheduleDto;
import com.sh.app.schedule.entity.Schedule;
import com.sh.app.schedule.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
@RequestMapping("/reservation")
/**
 * @RequestMapping은 클라이언트이 요청(url)에 맞는 클래스나 메서드를 연결시켜주는 어노테이션이다.
 * 이 어노테이션은 그 위치에 따라 의미가 다르다. 클래스 레벨 : 공통 주소 / 메서드 레벨에서 get/post 분류
 */
public class ReservationController {
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
    @GetMapping("/reservationBooking.do")
    public void reservationMain(Model model)
    {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        List<String> dateList = new ArrayList<>();
        List<String> dayOfWeekList = new ArrayList<>();

        //2주[14일]
        for (int i = 0; i < 15; i++) {
            LocalDate date = currentDate.plusDays(i);
            dateList.add(date.format(formatter));
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            dayOfWeekList.add(dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN)); //영문 요일을 한글요일[축약버전]으로
        }

        //0214 db 조회하여 현재 table에 있는 모든 영화 가져오기.
        List<Movie> movies;
        movies = movieService.findAllByOrderByTitleAsc(); //가나다순으로 정렬
        log.debug("movies = {}", movies);

        //0214 db 조회하여 지역정보 가져오기
        List<LocationDto> locationsWithCinemas = locationService.findAllLocationsWithCinemas();
//        model.addAttribute("locations", locationsWithCinemas);

        //0215 db 조회하여 상영정보 가져오기
//        List<Schedule> schedules = scheduleService.findAll();
//        System.out.println("==========상영스케쥴 출력하기==========");
//        for(int i=0;i<schedules.size();i++)
//        System.out.println(schedules.get(i).getId());

        //0219 dto로 변환한 스케쥴 잘 나오나
        List<ScheduleDto> scheduleDtos = scheduleService.findAllSchedulesDto();
        System.out.println("==========상영스케쥴 출력하기==========");
        System.out.println(scheduleDtos);

        //0214 db조회 영화 결과값+ 날짜값 + 지역 결과값 묶어서 model값을 보내주고싶을때 map을 이용해서 여러개를 전달 할 수 있다.
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("movies", movies); //영화정보
        dataMap.put("dateList", dateList); // 날짜 [로컬에서 계산함]
        dataMap.put("dayOfWeekList", dayOfWeekList); //요일 [로컬에서 계산함]
        dataMap.put("locations",locationsWithCinemas); //지역정보
        dataMap.put("scheduleDtos",scheduleDtos);//상영스케쥴[일단 상관없이 모두 갖고옴]

        // Model에 데이터 저장
        model.addAttribute("dataMap", dataMap);
    }

    //0216 다음 버튼 눌렀을 때
    @GetMapping("/scheduleTest")
    public ResponseEntity<?> getScheduleTest(@RequestParam("scheduleId") int scheduleId,Model model,
                                             @AuthenticationPrincipal MemberDetails memberDetails)
    {
        log.debug("======================memberDetails = {}", memberDetails);
        // 요청 처리
        System.out.println("================================"+scheduleId);
        String result = "Result for scheduleId " + scheduleId;

        //test용으로 schedule 조회
        List<Schedule> schedules;
        schedules = scheduleService.findAll();
        System.out.println(schedules.get(0));
        //model.addAttribute("testmovie", movies);

        //0217 test 용으로 좌석값을 가진 배열을 모델에 담아 전송[예약된 좌석 체크용으로 쓰기 위함]
        String[] testSeat = {"A01","A10","B05","B06","C03","F06","F09"};

        //다음 예매 작업으로 넘어갈 때 현재 로그인이 되어있는지 안되어있는지 memberDetails로 확인함.
        if (memberDetails != null) {
            System.out.println("지금 로그인한 유저의 이름은???:"+memberDetails.getUsername());
            model.addAttribute("membername", memberDetails.getUsername());

            //model.addAttribute("testSeat", testSeat);
            //reservationBooking 페이지 내의 갱신하려는 특
            return ResponseEntity.ok(testSeat);//"reservationBooking";// :: #test-area";
        }
        else {
            //로그인하지 않았다면 예매기능을 사용할 수 없으므로 로그인 페이지로 이동시키자.
           // return "redirect:/auth/login.do";
           //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("auth/login.do");
           // return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(testSeat);

    }


    //0219 영화 + 극장 + 시간 조합으로 상영 정보 찾기

    @GetMapping("/findSchedule")
    public ResponseEntity<?> findSchedule(@RequestParam("scheduleId") int scheduleId,Model model,
                                             @AuthenticationPrincipal MemberDetails memberDetails)
    {
        log.debug("======================memberDetails = {}", memberDetails);
        // 요청 처리
        System.out.println("================================"+scheduleId);
        String result = "Result for scheduleId " + scheduleId;

        //test용으로 schedule 조회
        List<Schedule> schedules;
        schedules = scheduleService.findAll();
        System.out.println(schedules.get(0));
        //model.addAttribute("testmovie", movies);

        //0217 test 용으로 좌석값을 가진 배열을 모델에 담아 전송[예약된 좌석 체크용으로 쓰기 위함]
        String[] testSeat = {"A01","A10","B05","B06","C03","F06","F09"};

        //다음 예매 작업으로 넘어갈 때 현재 로그인이 되어있는지 안되어있는지 memberDetails로 확인함.
        if (memberDetails != null) {
            System.out.println("지금 로그인한 유저의 이름은???:"+memberDetails.getUsername());
            model.addAttribute("membername", memberDetails.getUsername());

            //model.addAttribute("testSeat", testSeat);
            //reservationBooking 페이지 내의 갱신하려는 특
            return ResponseEntity.ok(testSeat);//"reservationBooking";// :: #test-area";
        }
        else {
            //로그인하지 않았다면 예매기능을 사용할 수 없으므로 로그인 페이지로 이동시키자.
            // return "redirect:/auth/login.do";
            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("auth/login.do");
            // return ResponseEntity.status(500).build();

        }
        return ResponseEntity.ok(testSeat);

    }




    @GetMapping("/reservationMain1.do")
    public void reservationMain() {

    }
}
