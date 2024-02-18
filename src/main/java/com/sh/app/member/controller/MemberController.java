package com.sh.app.member.controller;


import com.sh.app.auth.vo.MemberDetails;
import com.sh.app.member.dto.MemberCreateDto;
import com.sh.app.member.entity.Member;
import com.sh.app.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

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
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/createMember.do")
    public void createMember() {}

    //회원가입 post - 이후 리다이렉트
    @PostMapping("/createMember.do")
    public String CreateMember(
            @Valid MemberCreateDto memberCreateDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
            throw new RuntimeException(message);
        }
        log.debug("memberCreateDto = {}", memberCreateDto);

        Member member = memberCreateDto.toMember();
        String encodedPassword = passwordEncoder.encode(member.getMemberPwd());
        member.setMemberPwd(encodedPassword);

        member = memberService.createMember(member);

        redirectAttributes.addFlashAttribute("msg", "반갑습니다." + member.getMemberName() + "님😀");
        return "redirect:/auth/login.do";
    }

    @PostMapping("/checkIdDuplicate.do")
    public ResponseEntity<?> checkIdDuplicate(@RequestParam("username") String username) { // ? -> Object
        Map<String, Object> resultMap = Map.of(
                "available",
                memberService.findByMemberLoginId(username) == null
        );
        return ResponseEntity.ok(resultMap);
    }

    @GetMapping("/memberDetail.do")
    public void memberDetail(Authentication authentication, @AuthenticationPrincipal MemberDetails memberDetails) {
        log.debug("authentication = {}", authentication);
        log.debug("memberDetails = {}", memberDetails);
    }
}
