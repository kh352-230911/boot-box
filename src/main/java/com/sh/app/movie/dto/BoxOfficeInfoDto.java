package com.sh.app.movie.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoxOfficeInfoDto {
    private Integer rank;

    @JsonProperty("movieNm")
    private String title;

    @JsonProperty("openDt")
    private LocalDate releaseDate;

    @JsonProperty("movieCd")
    private Long id;
}
