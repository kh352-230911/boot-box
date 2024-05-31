package com.sh.app.movieList.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Data
public class AddMovieListDto
{
    @Id
    @GeneratedValue(generator = "seq_movie_list_id_generator")
    @SequenceGenerator(
            name = "seq_movie_list_id_generator",
            sequenceName = "seq_movie_list_id",
            allocationSize = 1
    )
    private Long id;
    private Long cinemaId;
    private long movieId;
}
