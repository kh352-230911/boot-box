package com.sh.app.admin.controller;

import com.sh.app.member.entity.Member;
import com.sh.app.member.service.MemberService;
import com.sh.app.notice.entity.Notice;
import com.sh.app.notice.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private NoticeService noticeService;

    @GetMapping("/memberList.do")
    public void memberList(Model model) {
        List<Member> members = memberService.findAll();
        log.debug("members = {}", members);
        model.addAttribute("members", members);
        System.out.println("회원조회 controller" + members);
    }

    @GetMapping("/notice.do")
    public void notice(Model model) {
        List<Notice> notices = noticeService.findAll();
        log.debug("notices = {}", notices);
        model.addAttribute("notices", notices);
        System.out.println("공지조회 controller" + notices);

    }

    @GetMapping("/answer.do")
    public void answer() {

    }
}