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
import com.sh.app.schedule.dto.ScheduleDto;
import com.sh.app.schedule.dto.ScheduleListDto;
import com.sh.app.schedule.service.ScheduleService;
import com.sh.app.theater.dto.TheaterDto;
import com.sh.app.theater.service.TheaterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/adminManagement.do")
    public void adminManagement(Model model) {
        List<AdminListDto> adminListDtos = adminService.findAllWithCinema();

//        log.debug("adminListDtos = {}", adminListDtos);

        model.addAttribute("adminList", adminListDtos);
    }

    @GetMapping("/approvalManagement.do")
    public void approvalManagement() {
    }
}

