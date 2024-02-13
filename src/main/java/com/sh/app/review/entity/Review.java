package com.sh.app.review.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "review")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    @Id
    private Long id;
    private String reservationId; // fk-예약아이디
    private Long memberId; // fk-회원아이디
    private int reviewScore; // 리뷰평점
    private String reviewDetail; // 리뷰내용
    private LocalDateTime reviewCreatedAt; // 리뷰작성날짜

}
