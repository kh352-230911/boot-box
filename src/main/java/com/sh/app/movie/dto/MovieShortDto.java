package com.sh.app.movie.dto;

import com.sh.app.movieData.entity.Rating;
import lombok.Data;

@Data

public class MovieShortDto {
    private Long id;
    private String title;
    private String posterUrl;
    private String filmRatings;
}
