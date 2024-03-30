package com.sh.app.memberLikeGenre.controller;

import com.sh.app.genre.entity.Genre;
import com.sh.app.genre.serviece.GenreServiece;
import com.sh.app.memberLikeGenre.dto.MemberLikeGenreListDto;
import com.sh.app.memberLikeGenre.entity.MemberLikeGenre;
import com.sh.app.memberLikeGenre.serviece.MemberLikeGenreServiece;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/")
public class MemberLikeGenreController {

    @Autowired
    private MemberLikeGenreServiece memberLikeGenreServiece;

    @Autowired
    private GenreServiece genreServiece;

    @GetMapping("GenerLike")
    public ResponseEntity<?> GenerLikeList(@RequestParam Long memberId, Model model) {

        try {
            MemberLikeGenreListDto memberLikeGenre = memberLikeGenreServiece.findMemberLikeGenreInfoByMemberId(memberId);
            log.debug("memberLikeGenre = {}", memberLikeGenre);

//            Genre genre = genreServiece.findByGenreId(memberLikeGenre.getGenreId());
//            log.debug("genreName = {}", genre);
            model.addAttribute("memberLikeGenre", memberLikeGenre);


            return ResponseEntity.ok(memberLikeGenre);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
