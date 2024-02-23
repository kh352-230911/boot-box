package com.sh.app.memberLikeCinema.controller;

import com.sh.app.memberLikeCinema.dto.CreateMemberLikeCinemaDto;
import com.sh.app.memberLikeCinema.entity.MemberLikeCinema;
import com.sh.app.memberLikeCinema.serviece.MemberLikeCinemaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/cinema")
public class MemberLikeCinemaController {

    @Autowired
    private MemberLikeCinemaService memberLikeCinemaService;

    @PostMapping("/cinemaLike")
    public ResponseEntity<?> bookMarkCreate(CreateMemberLikeCinemaDto createMemberLikeCinemaDto) {

        int currentCount = memberLikeCinemaService.countByMemberId(createMemberLikeCinemaDto.getMemberId());
        log.debug("currentCount = {}", currentCount);
        if (currentCount >= 3) {
            // 이미 3개의 극장을 북마크하면 성공응답
            return ResponseEntity.ok(currentCount);
        }
        MemberLikeCinema memberLikeCinema =  memberLikeCinemaService.bookMarkCreate(createMemberLikeCinemaDto);

        return ResponseEntity.ok(memberLikeCinema);
    }


    @PostMapping("/cinemaNoLike")
    public ResponseEntity<?> bookMarkDelete(@RequestParam Long cinemaId) {
        int result = memberLikeCinemaService.deleteByCinemaId(cinemaId);
        log.debug("result = {}", result);
        return ResponseEntity.ok(result);
    }
    
}
