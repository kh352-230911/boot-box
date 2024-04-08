package com.sh.app.review.controller;

import com.sh.app.auth.vo.MemberDetails;
import com.sh.app.review.dto.CreateReviewDto;
import com.sh.app.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

}
