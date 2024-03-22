package com.sh.app.movieGenre.entity;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;
}
