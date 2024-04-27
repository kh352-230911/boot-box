package com.sh.app.movieList.dto;

import com.sh.app.cinema.entity.Cinema;
import com.sh.app.movie.entity.Movie;
import lombok.Data;

@Data
public class ShowMovieListDto
{
    private Long id;
    private Long cinemaId;
    private Long movieId;
    private String movieTitle;
    private String moviePosterUrl;
}
