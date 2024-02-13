package com.sh.app.movie.dto;

import com.sh.app.movie.entity.Rating;
import com.sh.app.review.entity.Review;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MovieListDto {
    private Long id;
    private String title;
    private String trailer;
    private String poster;
    private double advanceReservation;
    private double avgReviewScore;
}
