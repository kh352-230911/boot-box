package com.sh.app.movie.dto;

import lombok.Data;

@Data

public class MovieShortDto {
    private Long id;
    private String title;
    private String posterUrl;
    private String filmRatings;
}
