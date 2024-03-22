package com.sh.app.movie.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sh.app.actor.dto.ActorDetailDto;
import com.sh.app.director.dto.DirectorDetailDto;
import com.sh.app.genre.dto.GenreDetailDto;
import com.sh.app.genreData.entity.GenreData;
import com.sh.app.movieActor.entity.MovieActor;
import com.sh.app.movieData.entity.Rating;
import com.sh.app.movieDirector.entity.MovieDirector;
import com.sh.app.movieGenre.entity.MovieGenre;
import com.sh.app.review.entity.Review;
import com.sh.app.vod.dto.VodDetailDto;
import com.sh.app.vod.dto.VodDto;
import com.sh.app.vod.dto.VodInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDetailDto {
    private Long id;

    private Integer rank;

    private String title;

    private LocalDate releaseDate;

    private String filmRatings;

    private Integer runtime;

    private String overview;

    private Double voteAverage;

    private String posterUrl;

    private boolean searchResult;

    private String searchVaule;

    @JsonIgnore // 특정필드 제거
    private List<Review> reviews;

    private List<VodDetailDto> vodDetailDtos;

    private List<GenreDetailDto> genreDetailDtos;

    private List<ActorDetailDto> actorDetailDtos;

    private List<DirectorDetailDto> directorDetailDtos;
}
