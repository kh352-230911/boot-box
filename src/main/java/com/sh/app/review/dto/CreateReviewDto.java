package com.sh.app.review.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateReviewDto {
    private String reservationId;

    private Long memberId;

    private Long movieId;

    private Double reviewScore;

    private String reviewDetail;
}
