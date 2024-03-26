package com.sh.app.movie.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sh.app.movieActor.entity.MovieActor;
import com.sh.app.movieDirector.entity.MovieDirector;
import com.sh.app.movieGenre.entity.MovieGenre;
import com.sh.app.review.entity.Review;
import com.sh.app.schedule.entity.Schedule;
import com.sh.app.vod.entity.Vod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {
    @Id
    private Long id;

    private Integer rank;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String title; // 원본 제목

    private String normalizedTitle; // 정규화된 제목

    private LocalDate releaseDate;

    private String filmRatings;

    private Integer runtime;

    private String overview;

    private Double voteAverage;

    private String posterUrl;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @BatchSize(size = 50)
    private List<Vod> vods = new ArrayList<>();

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @BatchSize(size = 50)
    private List<MovieGenre> movieGenres = new ArrayList<>();

    public void addMovieGenre(MovieGenre movieGenre) {
        movieGenres.add(movieGenre);
        movieGenre.setMovie(this);
    }

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @BatchSize(size = 50)
    private List<MovieActor> movieActors = new ArrayList<>();

    public void addMovieActor(MovieActor movieActor) {
        movieActors.add(movieActor);
        movieActor.setMovie(this);
    }

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @BatchSize(size = 50)
    private List<MovieDirector> movieDirectors = new ArrayList<>();

    public void addMovieDirector(MovieDirector movieDirector) {
        movieDirectors.add(movieDirector);
        movieDirector.setMovie(this);
    }

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    @Builder.Default
//    private List<Review> reviews = new ArrayList<>();
}
