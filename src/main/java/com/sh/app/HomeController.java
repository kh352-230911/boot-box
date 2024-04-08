package com.sh.app;

import com.sh.app.memberLikeGenre.dto.MemberLikeGenreListDto;
import com.sh.app.movie.dto.MovieDetailDto;
import com.sh.app.movie.service.MovieService;
import com.sh.app.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@Slf4j
public class HomeController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/")
    public String home(@RequestParam(value = "search", required = false) String search, Model model) {
        System.out.println("~~~~~~~~~~~~~~home~~~~~~~~~~~~~~~~~");
        executeTask();
        List<MovieDetailDto> movieDetailDtos;
        if(search == null) {
            movieDetailDtos = movieService.findFirst6ByOrderByRankAsc();
            log.debug("movieDetailDtos = {}", movieDetailDtos);

        }
        else {
            movieDetailDtos = movieService.findByTitleContaining(search);
            model.addAttribute("search", search);
            log.debug("movieDetailDtos = {}", movieDetailDtos);

        }
        model.addAttribute("movies", movieDetailDtos);
        return "index";
    }

    @Scheduled(cron = "0 8 17 * * *", zone = "Asia/Seoul")
    public void executeTask() {
        // 실행할 작업 내용을 여기에 작성
        System.out.println("스케쥴 테스트 - 작업이 실행되었습니다.");
    }


}
