package com.sh.app.movie.dto;



import com.sh.app.genre.entity.Genre;
import com.sh.app.movie.entity.Rating;
import com.sh.app.review.dto.ReviewListDto;
import com.sh.app.review.entity.Review;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
public class MovieDetailDto {
    private Long id;
    private String title;
    private Rating filmRatings;
    private String releaseDate;
    private int runningTime;
    private String trailer;
    private String poster;
    private String director;
    private String actor;
    private String summary;
    private double advanceReservation;
    private List<Review> reviews = new ArrayList<>();

    private Set<Genre> genres = new LinkedHashSet<>();

}
