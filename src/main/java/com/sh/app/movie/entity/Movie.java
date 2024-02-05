package com.sh.app.movie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movie")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Rating fileRatings;
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
}
