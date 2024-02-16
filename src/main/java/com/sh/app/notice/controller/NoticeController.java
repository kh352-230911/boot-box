package com.sh.app.notice.controller;


import com.sh.app.notice.dto.NoticeListDto;
import com.sh.app.notice.entity.Notice;
import com.sh.app.notice.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/noticeList.do")
    public void noticeList(Model model) {
        List<NoticeListDto> notices = noticeService.findAll();
        log.debug("notices = {}", notices);
        model.addAttribute("notices", notices);
        System.out.println("공지조회 controller" + notices);

    }

    @GetMapping("/noticeDetail.do")
    public void noticeDetail() {

    }
}
