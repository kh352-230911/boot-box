package com.sh.app.movie.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class KmdbMovieInfoDto {
    @JsonProperty("plots")
    private Plots overview;

    private Integer runtime;

    @JsonProperty("rating")
    private String filmRatings;

    @JsonProperty("posters")
    private String posterUrl;

    private Actors actors;

    private Directors directors;

    private String genre;

    private Vods vods;
}
