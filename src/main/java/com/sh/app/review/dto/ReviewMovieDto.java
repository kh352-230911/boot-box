package com.sh.app.review.dto;

import com.sh.app.movie.dto.MovieInfoDto;
import com.sh.app.movie.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewMovieDto {
    private Long id;

    private Integer reviewScore;

    private String reviewDetail;

    private LocalDateTime reviewCreatedAt;

    private MovieInfoDto movie;
}
