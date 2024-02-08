package com.sh.app.moviegenre.entity;

import com.sh.app.genre.entity.Genre;
import com.sh.app.movie.entity.Movie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movie_genre")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genreId;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movieId;

}
