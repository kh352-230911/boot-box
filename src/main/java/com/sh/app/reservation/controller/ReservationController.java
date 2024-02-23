package com.sh.app.reservation.controller;
import com.sh.app.auth.vo.MemberDetails;
import com.sh.app.location.dto.LocationDto;
import com.sh.app.location.service.LocationService;
import com.sh.app.member.entity.Member;
import com.sh.app.movie.dto.MovieListDto;
import com.sh.app.movie.dto.MovieShortDto;
import com.sh.app.movie.entity.Movie;
import com.sh.app.movie.service.MovieService;
import com.sh.app.reservation.service.ReservationService;
import com.sh.app.schedule.dto.IScheduleInfoDto;
import com.sh.app.schedule.dto.ScheduleDto;
import com.sh.app.schedule.entity.Schedule;
import com.sh.app.schedule.service.ScheduleService;
import com.sh.app.seat.entity.SeatDto;
import com.sh.app.seat.service.SeatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import com.siot.IamportRestClient.IamportClient;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
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

    @Autowired
    SeatService seatService;
    private IamportClient iamportClient;

    @Value("${imp.api.key}")
    private String apiKey;

    @Value("${imp.api.secretkey}")
    private String secretKey;

    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient(apiKey, secretKey);
    }


    //첫 예매 페이지 진입 시 날짜(로컬)
    @GetMapping("/reservationBooking.do")
    public void reservationMain(Model model)
    {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof MemberDetails) {
            MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
            // 여기에서 memberDetails를 사용하여 사용자의 모든 정보에 접근할 수 있습니다.
            System.out.println(memberDetails.getMember().getMemberPhone());
        }
        else {
            System.out.println("로그인한 상태가 아닙니다......");
        }




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
        System.out.println("movies start======================================================================================");
        List<MovieShortDto> movieList;
        movieList = movieService.shotMovie(); //가나다순으로 정렬
        log.debug("movies = {}", movieList);
        System.out.println("movies end======================================================================================");
        System.out.println("locationsWithCinemas start======================================================================================");
        //0214 db 조회하여 지역정보 가져오기
        List<LocationDto> locationsWithCinemas = locationService.findAllLocationsWithCinemas();
//        model.addAttribute("locations", locationsWithCinemas);z

        //0215 db 조회하여 상영정보 가져오기
//        List<Schedule> schedules = scheduleService.findAll();
//        System.out.println("==========상영스케쥴 출력하기==========");
//        for(int i=0;i<schedules.size();i++)
//        System.out.println(schedules.get(i).getId());
        System.out.println("locationsWithCinemas end======================================================================================");
        //0219 dto로 변환한 스케쥴 잘 나오나
        List<ScheduleDto> scheduleDtos = scheduleService.findAllSchedulesDto();
        System.out.println("==========상영스케쥴 출력하기==========");
        System.out.println(scheduleDtos);

        //0214 db조회 영화 결과값+ 날짜값 + 지역 결과값 묶어서 model값을 보내주고싶을때 map을 이용해서 여러개를 전달 할 수 있다.
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("movies", movieList); //영화정보
        dataMap.put("dateList", dateList); // 날짜 [로컬에서 계산함]
        dataMap.put("dayOfWeekList", dayOfWeekList); //요일 [로컬에서 계산함]
        dataMap.put("locations",locationsWithCinemas); //지역정보

        //초기 세팅때는 dto가 아닌 init 문구만 띄워준다.
        dataMap.put("scheduleDtos","영화,극장,시간을 선택해주세요 :)");

        // Model에 데이터 저장
        model.addAttribute("dataMap", dataMap);
    }











    //0220 영화-극장-날짜를 순차적으로 눌렀다고 가정했을 경우 조건에 맞는 상영정보를 갖고온다.
    @GetMapping("/findSchedules")
    public ResponseEntity<?> getScheduleTest(@RequestParam("movieId") Long movieId,
                                             @RequestParam("cinemaId") Long cinemaId,
                                             @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate dateId,
                                             Model model
                                             )
    {
        System.out.println("====findSchedules====");
        System.out.println("movieId:"+movieId+"/cinemaId:"+cinemaId+"/dateId"+dateId);

        //파라메터값 movieId, cinemaId, dateId로 쿼리 조회 6 201 '2024/02/20'
        //--소풍:6 / 강남점:201/상영관:20104(4관)/ 날짜:2월20일

        log.debug("cinemaId = {}", cinemaId); // 극장 ID
        log.debug("selectedDate = {}", dateId); // 선택된날짜
        List<IScheduleInfoDto> scheduleDetails = scheduleService.findScheduleDetailsByDateAndCinemaId_2(movieId, cinemaId, dateId);
        log.debug("scheduleDetails = {}", scheduleDetails);

        // 로직을 추가하여 scheduleDetails에서 필요한 JSON 구조로 변환
        List<Map<String, Object>> organizedSchedules = organizeSchedules(scheduleDetails);
        log.debug("organizedSchedules = {}", organizedSchedules);


        // JSON 구조로 클라이언트에 반환
        //0221 해당하는 스케쥴이 없는 경우 까지 고려.
        if(organizedSchedules.isEmpty()) {
            System.out.println("organizedSchedules is empty.....");
            return ResponseEntity.status(404).build(); //204:del하여 없는 경우, 404:쿼리 실행했으나 select 결과를 찾을 수 없는 경우
        }
        else {
            return ResponseEntity.ok(organizedSchedules);

        }

    }

    private List<Map<String, Object>> organizeSchedules(List<IScheduleInfoDto> scheduleDetails) {
        // 영화별, 상영관별, 스케줄별 그룹화하기위한 맵
        Map<String, Map<String, List<Map<String, Object>>>> organized = new HashMap<>();
        // 각 영화별 상영 시간을 저장하기 위한 맵
        Map<String, String> movieDurations = new HashMap<>();

        for (IScheduleInfoDto dto : scheduleDetails) {
            Long movieId = dto.getMovieId();
            Long cinemaId = dto.getCinemaId();
            Long schId = dto.getSchId();

            // 예약 페이지 URL 생성
            String bookingUrl = String.format("/bootbox/reservation/reservationBooking.do?movieId=%d&cinemaId=%d&schId=%d", movieId, cinemaId, schId);

            String title = dto.getMovieTitle();
            String theater = dto.getTheaterName();
            String runningTime = dto.getRunningTime();

            movieDurations.putIfAbsent(title, runningTime); // 영화 제목에 해당하는 상영 시간을 맵에 저장

            // 영화 시간, 남은 좌석 그룹화 및 예약 페이지 링크 추가
            Map<String, Object> timeMap = new HashMap<>();
            timeMap.put("schId",schId);
            timeMap.put("time", dto.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")));
            timeMap.put("seatsAvailable", dto.getRemainingSeats());
            timeMap.put("bookingUrl", bookingUrl); // 예약 페이지로 이동할 URL 추가

            // 영화 제목별, 상영관별, 스케줄별에 따라 그룹화
            organized.computeIfAbsent(title, k -> new HashMap<>())
                    .computeIfAbsent(theater, k -> new ArrayList<>())
                    .add(timeMap);
        }

        // 데이터 가공하여 최종적으로 필요한 상영일정 구조로 변환
        List<Map<String, Object>> finalStructure = new ArrayList<>();
        organized.forEach((movieTitle, theaters) -> {
            // 영화 제목별 그룹화
            Map<String, Object> movieMap = new HashMap<>();
            movieMap.put("title", movieTitle);
            movieMap.put("totalDuration", movieDurations.get(movieTitle)); // 영화 제목에 해당하는 상영 시간을 사용

            // 상영관별 스케줄 그룹화
            List<Map<String, Object>> theaterList = new ArrayList<>();
            theaters.forEach((theaterName, timesList) -> {
                Map<String, Object> theaterMap = new HashMap<>();
                theaterMap.put("theater", theaterName);
                theaterMap.put("times", timesList);
                theaterList.add(theaterMap);
            });

            movieMap.put("schedules", theaterList);
            finalStructure.add(movieMap);
        });

        return finalStructure;
    }


    @GetMapping("/detailSchedule")
    public ResponseEntity<?> findSchedule(@RequestParam("scheduleId") Long scheduleId,Model model
                                           //  ,@AuthenticationPrincipal MemberDetails memberDetails
    )
    {

        if (isAuthenticated()) {
            getUserInfo();
            // 요청 처리
            System.out.println("============ 넘겨받은 상영 스케쥴 id============="+scheduleId);
            //좌석조회용 dto
            List<SeatDto> seatDtos;
            seatDtos = seatService.findSeatsByScheduleId(scheduleId);
            System.out.println("===해당 스케쥴에 예약된 좌석===");
            System.out.println(seatDtos);
            //model.addAttribute("testmovie", movies);
            //[SeatDto(id=23, name=C03), SeatDto(id=30, name=C10)]
            return ResponseEntity.ok(seatDtos);
        }
        else {
            // 로그인 인증 실패 시 로그인 화면 진입
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("auth/login.do");
        }

    }


    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 사용자가 인증되었는지 확인
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            return true;
        }

        return false;
    }

    private void getUserInfo()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof MemberDetails) {
            MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
            // 여기에서 memberDetails를 사용하여 사용자의 모든 정보에 접근할 수 있습니다.
        }

// 로그인한 사용자정보를 가진 객체를 얻습니다.

    }





    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken)
        {
            return false;
        }
        return authentication.isAuthenticated();
    }
}
