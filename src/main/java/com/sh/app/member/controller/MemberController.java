package com.sh.app.member.controller;


import com.sh.app.answer.dto.AnswerDetailDto;
import com.sh.app.answer.service.AnswerService;
import com.sh.app.ask.dto.AskDetailDto;
import com.sh.app.ask.service.AskService;
import com.sh.app.auth.service.AuthService;
import com.sh.app.auth.vo.MemberDetails;
import com.sh.app.genre.entity.Genre;
import com.sh.app.genre.repository.GenreRepository;
import com.sh.app.genre.serviece.GenreServiece;
import com.sh.app.member.dto.*;
import com.sh.app.member.entity.Member;
import com.sh.app.member.service.MemberService;
import com.sh.app.memberLikeCinema.dto.MemberLikeCinemaListDto;
import com.sh.app.memberLikeCinema.serviece.MemberLikeCinemaService;
import com.sh.app.memberLikeGenre.entity.MemberLikeGenre;
import com.sh.app.memberLikeGenre.repository.MemberLikeGenreRepository;
import com.sh.app.review.dto.CreateReviewDto;
import com.sh.app.review.entity.Review;
import com.sh.app.review.service.ReviewService;
import com.sh.app.util.GenreNormalization;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MemberLikeCinemaService memberLikeCinemaService;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MemberLikeGenreRepository memberLikeGenreRepository;

    @Autowired
    private GenreServiece genreServiece;

    @Autowired
    private AskService askService;

    @Autowired
    private AnswerService answerService;

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
//        log.debug("memberCreateDto = {}", memberCreateDto);

        Member member = memberCreateDto.toMember();
        String encodedPassword = passwordEncoder.encode(member.getMemberPwd());
        member.setMemberPwd(encodedPassword);

        // Member와 Genre 연결
        member = memberService.createMember(member);

        List<String> genres = memberCreateDto.getGenres();
        for (String genreName : genres) {
            String GenreName = GenreNormalization.normalizeGenreName(genreName);
            Genre genre = genreRepository.findByGenreName(GenreName).orElseThrow();

            MemberLikeGenre memberLikeGenre = MemberLikeGenre.builder()
                    .member(member)
                    .genre(genre)
                    .build();
            memberLikeGenreRepository.save(memberLikeGenre);
        }

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
    public void memberDetail(Authentication authentication,
                             @AuthenticationPrincipal MemberDetails memberDetails,
                             Model model) {
        List<MemberLikeCinemaListDto> memberLikeCinemaListDtos = memberLikeCinemaService.findByMemberId(memberDetails.getMember().getId());
        model.addAttribute("memberLikeCinemas", memberLikeCinemaListDtos);
    }

    @GetMapping("/updateMember.do")
    public void updateMember(@AuthenticationPrincipal MemberDetails memberDetails, Model model) {
        // 현재 로그인한 회원의 ID를 가져옵니다.
        Long memberId = memberDetails.getMember().getId();

        // 회원 정보 조회
        Member member = memberService.findByMemberId(memberId);

        // 회원의 선호하는 장르 목록 조회
        List<Genre> selectedGenres = memberService.findMemberLikeGenresByMemberId(memberId);

        // 모든 가능한 장르 목록 조회
        List<Genre> allGenres = genreServiece.findAll();

        // 모델에 데이터 추가
        model.addAttribute("member", member);
        model.addAttribute("selectedGenres", selectedGenres);
        model.addAttribute("allGenres", allGenres);
    }

    @PostMapping("/updateMember.do")
    public String updateMember(
            @Valid MemberUpdateDto memberUpdateDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal MemberDetails memberDetails,
            RedirectAttributes redirectAttributes) {
//        log.debug("memberUpdateDto = {}", memberUpdateDto);

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

        // 사용자의 선호 장르 업데이트를 위한 로직 호출
        memberService.updateMemberGenres(member, memberUpdateDto.getGenres());

//        memberService.updateMember(member);

        // security Authentication 갱신
        authService.updateAuthentication(member.getMemberLoginId());

        redirectAttributes.addFlashAttribute("msg", member.getMemberName() + "님의 회원 정보가 수정 되었습니다. 😀");

        return "redirect:/member/memberDetail.do";
    }

    @PostMapping("/deleteMember.do")
    public String deleteMember(Long id) {
//        log.debug("id = {}", id);
        memberService.deleteById(id);
        memberService.logoutAndInvalidateSession();
        return "redirect:/";
    }

    @GetMapping("/memberReservation.do")
    public void memberReservation(Long id, Model model) {
        MemberReservationDto member = memberService.findByReservation(id);
//        log.debug("member = {}", member);
        model.addAttribute("member", member);
    }

    @GetMapping("/memberWatchedMovie.do")
    public void memberWatchedMovie(Long id, Model model) {
//        log.debug("id = {}", id);
        MemberReservationDto member = memberService.findPastReservationsById(id);
//        log.debug("member = {}", member);

        model.addAttribute("member", member);
    }

    @PostMapping("/memberWatchedMovie.do")
    public String  createReview(@Valid CreateReviewDto createReviewDto,
                                @AuthenticationPrincipal MemberDetails memberDetails,
                                RedirectAttributes redirectAttributes) {
//        log.debug("createReviewDto = {}", createReviewDto);

        reviewService.createReview(createReviewDto, memberDetails.getMember());

        redirectAttributes.addFlashAttribute("msg", memberDetails.getMember().getMemberName() + "님의 리뷰가 등록되었습니다.🤗");
        return "redirect:/member/memberReviewList.do?id=" + memberDetails.getMember().getId();
    }

    @GetMapping("/memberAskList.do")
    public void memberAskList(Long id, Model model) {
        MemberAskDto member = memberService.findById(id);

//        log.debug("member = {}", member);
        model.addAttribute("member", member);
    }

    @GetMapping("/memberReviewList.do")
    public void memberReviewList(Long id, Model model) {
        MemberReviewDto member = memberService.getMemberWithReviews(id);

//        log.debug("member = {}", member);
        model.addAttribute("member", member);
    }

    @PostMapping("/existingCheckIdDuplicate.do")
    public ResponseEntity<?> existingCheckIdDuplicate(@RequestParam("username") String username,
                                                      @RequestParam(value = "memberId", required = false) Long memberId) {
        boolean isAvailable;
        Member existingMember = memberService.findByMemberLoginId(username);

        if (existingMember == null) {
            // 사용자 이름이 사용되지 않았으므로 사용 가능
            isAvailable = true;
        } else if (memberId != null && existingMember.getId().equals(memberId)) {
            // 사용자 이름이 현재 회원의 것이므로 사용 가능
            isAvailable = true;
        } else {
            // 그 외의 경우는 사용 불가
            isAvailable = false;
        }

        Map<String, Object> resultMap = Map.of("available", isAvailable);
        return ResponseEntity.ok(resultMap);
    }

    @GetMapping("/preferredGenres")
    public ResponseEntity<?> getPreferredGenres(@AuthenticationPrincipal MemberDetails memberDetails) {
        Long memberId = memberDetails.getMember().getId();
        List<Genre> preferredGenres = memberService.getMemberPreferredGenres(memberId);

        List<Map<String, Object>> genresDto = preferredGenres.stream().map(genre -> {
            Map<String, Object> genreMap = new HashMap<>();
            genreMap.put("id", genre.getId());
            genreMap.put("genreName", genre.getGenreName());
            return genreMap;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(genresDto);
    }

    @GetMapping("/memberAskDetail.do")
    public void memberAskDetail(Long id, Model model) {
        AskDetailDto askDetailDto = askService.findById(id);

        Long askId = askDetailDto.getId();
        AnswerDetailDto answerDetailDto;

        try {
            answerDetailDto = answerService.findById(askId);
        } catch (Exception e) {
            answerDetailDto = null;
        }
        if (answerDetailDto == null) {
            model.addAttribute("answerForm", true);
        } else {
            model.addAttribute("showAnswer", answerDetailDto.getContent());
            model.addAttribute("answerForm", false);
        }

        model.addAttribute("ask", askDetailDto);
    }
}
