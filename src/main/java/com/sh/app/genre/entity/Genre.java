package com.sh.app.genre.entity;

import com.sh.app.moviegenre.entity.MovieGenre;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genre")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "genre_id")
    private Long id;
    private String genreList;
    @OneToMany
    @Builder.Default
    private List<MovieGenre> movieGenres = new ArrayList<>();
}
