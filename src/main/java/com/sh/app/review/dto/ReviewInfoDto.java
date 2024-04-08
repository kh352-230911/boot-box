package com.sh.app.review.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewInfoDto {
    private Long id;

    private Integer reviewScore;

    private String reviewDetail;

    private LocalDateTime reviewCreatedAt;
}
