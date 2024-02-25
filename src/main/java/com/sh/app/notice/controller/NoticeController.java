package com.sh.app.notice.controller;



import com.sh.app.notice.dto.NoticeCreateDto;
import com.sh.app.notice.dto.NoticeListDto;
import com.sh.app.notice.entity.Notice;
import com.sh.app.notice.service.NoticeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

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
    @GetMapping("/createNotice.do")
    public void createNotice() {

    }
    @PostMapping("/createNotice.do")
    public String createNotice(@Valid NoticeCreateDto noticeCreateDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        // 유효성 검사
        if (bindingResult.hasErrors()) {
            // 예외 던지기
            throw new RuntimeException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        // 공지사항을 데이터베이스에 저장
        noticeService.createNotice(noticeCreateDto);
        // 리다이렉트 후 알림
        redirectAttributes.addFlashAttribute("msg", "공지사항을 등록했습니다.");

        // 공지 저장 후 noticeList 페이지로 리다이렉션
        return "redirect:/notice/noticeList.do";
    }

    @PostMapping("/deleteNotice.do")
    public String deleteNotice(Long id, RedirectAttributes redirectAttributes) {
//        log.debug("id = {}", id);
//        System.out.println(id + "잘 왔나");
        noticeService.deleteById(id);
        redirectAttributes.addFlashAttribute("msg", "공지사항을 삭제했습니다.");
        return "redirect:/notice/noticeList.do";
    }

    @GetMapping("/noticeDetail.do")
    public void noticeDetail(Model model, Long id) {

        NoticeListDto notice = noticeService.findById(id);

        // System.getProperty("line.separator")는 현재 실행 중인 운영 체제의 기본 줄바꿈 문자열을 가져오는 메소드
        String br = System.getProperty("line.separator");
        // 운영 체제에 맞는 줄바꿈 문자를 가져와서 뷰에서 사용하기 위해 모델에 추가
        model.addAttribute("nlString", br);
        model.addAttribute("notice", notice);
    }
}
