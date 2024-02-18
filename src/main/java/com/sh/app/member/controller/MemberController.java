package com.sh.app.member.controller;


import com.sh.app.auth.service.AuthService;
import com.sh.app.auth.vo.MemberDetails;
import com.sh.app.member.dto.MemberCreateDto;
import com.sh.app.member.dto.MemberUpdateDto;
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
    @Autowired
    AuthService authService;

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

    @GetMapping("/updateMember.do")
    public void updateMember() {}

    @PostMapping("/updateMember.do")
    public String updateMember(
            @Valid MemberUpdateDto memberUpdateDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal MemberDetails memberDetails,
            RedirectAttributes redirectAttributes) {
        log.debug("memberUpdateDto = {}", memberUpdateDto);

        if(bindingResult.hasErrors()){
            StringBuilder message = new StringBuilder();
            bindingResult.getAllErrors().forEach((err) -> {
                message.append(err.getDefaultMessage() + " ");
            });
            throw new RuntimeException(message.toString());
        }

        // entity 업데이트
        Member member = memberDetails.getMember();
        member.setMemberName(memberUpdateDto.getMemberName());
        member.setMemberLoginId(memberUpdateDto.getMemberLoginId());
        String encodedPassword = passwordEncoder.encode(memberUpdateDto.getMemberPwd());
        member.setMemberPwd(encodedPassword);
        member.setMemberEmail(memberUpdateDto.getMemberEmail());
        member.setBirthyear(memberUpdateDto.getBirthyear());
        member.setMemberPhone(memberUpdateDto.getMemberPhone());

        memberService.updateMember(member);

        // security Authentication 갱신
        authService.updateAuthentication(member.getMemberLoginId());

        redirectAttributes.addFlashAttribute("msg", member.getMemberName() + "님의 회원 정보가 수정 되었습니다. 😀");

        return "redirect:/member/memberDetail.do";
    }

    @PostMapping("/deleteMember.do")
    public String deleteMember(Long id) {
        log.debug("id = {}", id);
        memberService.deleteById(id);

        memberService.logoutAndInvalidateSession();

        return "redirect:/";
    }
}
