package com.sh.app.movie.entity;

import com.sh.app.genre.entity.Genre;
import com.sh.app.review.entity.Review;
import com.sh.app.schedule.entity.Schedule;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "movie")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude =  "reviews")
public class Movie {
    @Id
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Rating filmRatings;
    @Column(nullable = false)
    private String releaseDate;
    @Column(nullable = false)
    private int runningTime;
    private String trailer;
    private String poster;
    private String director;
    private String actor;
    private String summary;
    private double advanceReservation;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
           name = "movie_genre",
           joinColumns = @JoinColumn(name = "movie_id"),
           inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @Builder.Default
    private Set<Genre> genres = new LinkedHashSet<>();

    public void addMovieGenre(Genre genre) {
            this.genres.add(genre);
    }

    @OneToMany(mappedBy = "movie", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Schedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "movie", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();

    public void addReview(Review review) {
        review.setMovie(this);
        this.reviews.add(review);
    }
}
