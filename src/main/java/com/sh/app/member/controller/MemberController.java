package com.sh.app.member.controller;


import com.sh.app.member.dto.MemberCreateDto;
import com.sh.app.member.entity.Member;
import com.sh.app.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 0206 hyejin
 *
 */

@Controller
@RequestMapping("/member")
@Slf4j
@Validated //유효성 검증 spring annotation
public class MemberController {
    @Autowired
    MemberService memberService;

    //회원가입 post - 이후 리다이렉트
    @PostMapping("/createMember.do")
    public String CreateMember(@Valid
                                   MemberCreateDto memberCreateDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes)
    {
        Member member = memberCreateDto.toMember();
        return "redirect:/";
    }
}
