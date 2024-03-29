package com.sh.app.member.dto;

import com.sh.app.review.dto.ReviewInfoDto;
import com.sh.app.review.dto.ReviewMovieDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberReviewDto {
    private List<ReviewMovieDto> reviews;
}
