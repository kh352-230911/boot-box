package com.sh.app.movie.dto;



//0221 영화 예매 첫 페이지에 출력할 용도로 만든 dto

import com.sh.app.movie.entity.Rating;
import lombok.Data;

/**
 * 필요한 것
 * 1.영화 id
 * 2.영화 title
 * 3.영화 poster
 * 4.영화 filmRatings(등급)
 */
@Data
public class MovieShortDto {
    private Long id;
    private String title;
    private String poster;
    private Rating filmRatings;
}
