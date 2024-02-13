package com.sh.app.review.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewListDto {
    private Long id;
    private String reservationId; // fk-예약아이디
    private long memberId; // fk-회원아이디
    private long movieId; // fk-영화아이디
    private int reviewScore; // 리뷰평점
    private String reviewDetail; // 리뷰내용
    private LocalDateTime reviewCreatedAt;
}
