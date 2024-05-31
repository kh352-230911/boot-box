package com.sh.app.movie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TmdbMovieInfoDto {
    private Long id;

    private String title;

    @JsonProperty("release_date")
    private LocalDate releaseDate;

    @JsonProperty("genre_ids")
    private List<Long> genreIds;

    private String overview;

    @JsonProperty("vote_average")
    private Double voteAverage;

    @JsonProperty("poster_path")
    private String posterUrl;
}
