package com.sh.app.admin.controller;

import com.sh.app.admin.dto.AdminListDto;
import com.sh.app.admin.entity.Admin;
import com.sh.app.admin.service.AdminService;
import com.sh.app.ask.entity.Ask;
import com.sh.app.ask.service.AskService;
import com.sh.app.cinema.dto.CinemaManagementDto;
import com.sh.app.cinema.entity.Cinema;
import com.sh.app.cinema.service.CinemaService;
import com.sh.app.member.entity.Member;
import com.sh.app.member.service.MemberService;
import com.sh.app.schedule.dto.ScheduleApprovalListDto;
import com.sh.app.auth.vo.MemberDetails;
import com.sh.app.cinema.dto.CinemaDto;
import com.sh.app.cinema.service.CinemaService;
import com.sh.app.member.entity.Member;
import com.sh.app.member.service.MemberService;
import com.sh.app.movie.dto.FindOtherMovieDto;
import com.sh.app.movie.dto.MovieListDto;
import com.sh.app.movie.service.MovieService;
import com.sh.app.movieList.service.MovieListService;
import com.sh.app.schedule.dto.ScheduleDto;
import com.sh.app.schedule.dto.ScheduleListDto;
import com.sh.app.schedule.service.ScheduleService;
import com.sh.app.theater.dto.TheaterDto;
import com.sh.app.theater.service.TheaterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private TheaterService theaterService;
    @Autowired
    private AskService askService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieListService movieListService;

    @GetMapping("/memberList.do")
    public void memberList(Model model) {
        List<Member> members = memberService.findAll();
//        log.debug("members = {}", members);
        model.addAttribute("members", members);
        System.out.println("회원조회 controller" + members);

        // 총 회원수
        int totalMembers = members.size();
        model.addAttribute("totalMembers", totalMembers);
        System.out.println("총 회원 수: " + totalMembers);
    }

//    @GetMapping("/noticeList.do")
//    public void notice(Model model) {
//        List<Notice> notices = noticeService.findAll();
//        log.debug("notices = {}", notices);
//        model.addAttribute("notices", notices);
//        System.out.println("공지조회 controller" + notices);
//    }

//    @GetMapping("/askList.do")
//    public void ask(Model model) {
//        List<Ask> asks = askService.findAll();
//        log.debug("asks = {}", asks);
//        model.addAttribute("asks", asks);
//        System.out.println("문의조회 controller" + asks);
//    }
    @PostMapping("/adminAuth.do")
    public String findByUsername(@RequestParam(value = "username") String username, RedirectAttributes redirectAttributes) {
//        log.debug("username = {}", username);
        Admin admin = adminService.findByUsername(username);
//        log.debug("admin = {}", admin);
        if (admin == null) {
            throw new UsernameNotFoundException(username);
        }
        return "redirect:/auth/adminLogin.do";
    }

    @GetMapping("/adminRegion.do")
    public void adminRegion(@RequestParam String name, Model model) {
        Admin admin = adminService.findByUsername(name);
        // 현재 관리자 확인
//        log.debug("admin = {}", admin);
        Long cinemaId = admin.getCinemaId();
        String region = cinemaService.findRegion(cinemaId);
        model.addAttribute("cinemaId", cinemaId);
        model.addAttribute("region", region);
        // 현재 관리지점 확인
//        log.debug("region = {}", region);
        List<TheaterDto> theaterDtos = theaterService.findAllTheatersWithCinemaId(cinemaId);
        CinemaDto cinemaDto = cinemaService.getCinemaDetails(cinemaId);
        List<MovieListDto> currentMovies = cinemaService.getMoviesByCinemaId(cinemaId); // 현재 상영 중인 영화 목록 가져오기
        log.debug("id = {}", cinemaId);
        model.addAttribute("cinema", cinemaDto);
        model.addAttribute("currentMovies", currentMovies); // 모델에 추가
        log.debug("cinemaDto = {}", cinemaDto);
        log.debug("currentMovies = {}", currentMovies);




        model.addAttribute("theaters", theaterDtos);
    }


    @PostMapping("/createTheater")
    public ResponseEntity<?> createTheater(
            @RequestParam(value = "theaterId") Long theaterId,
            @RequestParam(value = "cinemaId") Long cinemaId,
            @RequestParam(value = "theaterName") String theaterName,
            @RequestParam(value = "theaterSeat") int theaterSeat
           ) {
        theaterService.createTheater(theaterId, cinemaId, theaterName, theaterSeat);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deleteTheater")
    public ResponseEntity<?> deleteTheaterWithId(@RequestParam(value = "deleteId") Long deleteId) {
        theaterService.deleteTheaterWithId(deleteId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/insertScheduleList")
    public ResponseEntity<?> findSchedule(@RequestParam(value = "theaterId") Long theaterId) {
        List<ScheduleListDto> scheduleListDtos = scheduleService.findScheduleWithTheaterId(theaterId);
        log.debug("theaterId = {}", theaterId);
        for (ScheduleListDto scheduleListDto : scheduleListDtos) {
            log.debug("scheduleDto = {}", scheduleListDto);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/createSchedule")
    public ResponseEntity<?> createSchedule(
            @RequestParam(value = "sch_theaterId") Long sch_theaterId,
            @RequestParam(value = "sch_movieId") Long sch_movieId,
            @RequestParam(value = "sch_date") LocalDate sch_date,
            @RequestParam(value = "sch_startTime") String sch_startTime
    ) {
        scheduleService.createSchedule(sch_theaterId, sch_movieId, sch_date, sch_startTime);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addNewMovie")
    public ResponseEntity<?> addNewMovie(
            @RequestParam(value = "cinemaId") Long cinemaId,
            @RequestParam(value = "movieId") Long movieId) {
        movieListService.addNewMovie(cinemaId, movieId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/adminManagement.do")
    public void adminManagement(Model model) {
        List<AdminListDto> adminListDtos = adminService.findAllWithCinema();

//        log.debug("adminListDtos = {}", adminListDtos);

        model.addAttribute("adminList", adminListDtos);
    }

    @GetMapping("/approvalManagement.do")
    public void approvalManagement(Model model) {
        List<ScheduleApprovalListDto> scheduleApprovalListDtos = scheduleService.findAllScheduleApprovals();

        log.debug("scheduleApprovalListDtos = {}", scheduleApprovalListDtos);

        model.addAttribute("scheduleList", scheduleApprovalListDtos);
    }

    @PostMapping("/scheduleApprove")
    public ResponseEntity<?> scheduleApprove(@RequestParam(value = "scheduleId") Long id,
                                             @RequestParam(value = "approve") boolean approve) {
        log.debug("scheduleId = {}", id);
        log.debug("approve = {}", approve);
        scheduleService.approveSchedule(id, approve);

        return ResponseEntity.ok().build();
    }


    //현재 지점에서 상영중으로 내걸은 영화를 제외한 나머지 영화를 조회하는 메서드
    @GetMapping("/findOtherMovie")
    public ResponseEntity<?> findOtherMovie(@RequestParam(value = "cinemaId") Long cinemaId) {
        System.out.println("findOtherMovie - controller !cinemaId : "+cinemaId);
        List<FindOtherMovieDto> findOtherMovieDtos = movieService.findOtherMovie(cinemaId);
        log.debug("findOtherMovieDtos = {}", findOtherMovieDtos.size());
        return ResponseEntity.ok(findOtherMovieDtos);
    }

}

