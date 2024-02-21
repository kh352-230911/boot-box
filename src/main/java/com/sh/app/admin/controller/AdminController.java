package com.sh.app.admin.controller;

import com.sh.app.admin.entity.Admin;
import com.sh.app.admin.service.AdminService;
import com.sh.app.ask.entity.Ask;
import com.sh.app.ask.service.AskService;
import com.sh.app.member.entity.Member;
import com.sh.app.member.service.MemberService;
import com.sh.app.notice.entity.Notice;
import com.sh.app.notice.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private AskService askService;
    @Autowired
    private AdminService adminService;

    @GetMapping("/memberList.do")
    public void memberList(Model model) {
        List<Member> members = memberService.findAll();
        log.debug("members = {}", members);
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

    @GetMapping("/askList.do")
    public void ask(Model model) {
        List<Ask> asks = askService.findAll();
        log.debug("asks = {}", asks);
        model.addAttribute("asks", asks);
        System.out.println("문의조회 controller" + asks);
    }
    @PostMapping("/adminAuth.do")
    public String findByUsername(@RequestParam(value = "username") String username, RedirectAttributes redirectAttributes) {
        log.debug("username = {}", username);
        Admin admin = adminService.findByUsername(username);
        log.debug("admin = {}", admin);
        if (admin == null) {
            throw new UsernameNotFoundException(username);
        }
        return "redirect:/auth/adminLogin.do";
    }
}

//    @GetMapping("/adminAuth.do")
//    public String findByUsername(@RequestParam(value = "username") String username, RedirectAttributes redirectAttributes) {
//        log.debug("username = {}", username);
//        Admin admin = adminService.findByUsername(username);
//        log.debug("admin = {}", admin);
//        if (admin == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        return "/auth/adminLogin";
//    }

