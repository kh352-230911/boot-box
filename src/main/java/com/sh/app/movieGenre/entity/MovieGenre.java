package com.sh.app.movieGenre.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sh.app.genre.entity.Genre;
import com.sh.app.movie.entity.Movie;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "movie_genre")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"movie", "genre"})
@EqualsAndHashCode(of = "id")
public class MovieGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_movie_genre_id_generator")
    @SequenceGenerator(
            name = "seq_movie_genre_id_generator",
            sequenceName = "seq_movie_genre_id",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    @JsonBackReference
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;
}
