package com.sh.app.movieList.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sh.app.cinema.entity.Cinema;
import com.sh.app.genre.entity.Genre;
import com.sh.app.movie.entity.Movie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//0424각 지점마다 상영하는 영화가 다르기 때문에 이 entity(table)을 생성함.
@Entity
@Table(name="movie_list")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_movie_list_id_generator")
    @SequenceGenerator(
            name = "seq_movie_list_id_generator",
            sequenceName = "seq_movie_list_id",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    @JsonBackReference
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;
}
