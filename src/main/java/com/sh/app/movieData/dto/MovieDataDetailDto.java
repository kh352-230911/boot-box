//package com.sh.app.movieData.dto;
//
//
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.sh.app.genreData.entity.GenreData;
//import com.sh.app.movieData.entity.Rating;
//import com.sh.app.review.entity.Review;
//import lombok.Data;
//
//import java.util.ArrayList;
//import java.util.LinkedHashSet;
//import java.util.List;
//import java.util.Set;
//
//@Data
//public class MovieDataDetailDto {
//    private Long id;
//    private String title;
//    private Rating filmRatings;
//    private String releaseDate;
//    private int runningTime;
//    private String trailer;
//    private String poster;
//    private String director;
//    private String actor;
//    private String summary;
//    private double advanceReservation;
//    private Double avgReviewScore;
//    private boolean searchResult;
//    private String searchVaule;
//
//    @JsonIgnore // 특정필드 제거
//    private List<Review> reviews = new ArrayList<>();
//
//    private Set<GenreData> genreData = new LinkedHashSet<>();
//
//}
