//package com.sh.app.movieData.entity;
//
//import com.sh.app.genreData.entity.GenreData;
//import com.sh.app.review.entity.Review;
//import com.sh.app.schedule.entity.Schedule;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import java.util.LinkedHashSet;
//import java.util.Set;
//
//@Entity
//@Table(name = "movie")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@ToString(exclude =  {"schedules","reviews"})
//public class MovieData {
//    @Id
//    private Long id;
//    @Column(nullable = false)
//    private String title;
//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private Rating filmRatings;
//    @Column(nullable = false)
//    private String releaseDate;
//    @Column(nullable = false)
//    private int runningTime;
//    private String trailer;
//    private String poster;
//    private String director;
//    private String actor;
//    private String summary;
//    private double advanceReservation;
//
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//           name = "movie_genre",
//           joinColumns = @JoinColumn(name = "movie_id"),
//           inverseJoinColumns = @JoinColumn(name = "genre_id"))
//    @Builder.Default
//    private Set<GenreData> genreData = new LinkedHashSet<>();
//
//    public void addMovieGenre(GenreData genreData) {
//            this.genreData.add(genreData);
//    }
//
//    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
//    @Builder.Default
//    private List<Schedule> schedules = new ArrayList<>();
//
//    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
//    @Builder.Default
//    private List<Review> reviews = new ArrayList<>();
//
//    public void addReview(Review review) {
//        review.setMovieData(this);
//        this.reviews.add(review);
//    }
//}
