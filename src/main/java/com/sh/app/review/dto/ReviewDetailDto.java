package com.sh.app.review.dto;

import com.sh.app.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDetailDto {
    private Long id;

    private String reservationId; // fk-예약아이디

    private int reviewScore; // 리뷰평점

    private String reviewDetail;

    private LocalDateTime reviewCreatedAt; // 리뷰작성날짜

    private Long movieId;

    private Member member;
}
