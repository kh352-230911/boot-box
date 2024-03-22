package com.sh.app.movie.dto;

import com.sh.app.vod.dto.VodDetailDto;
import lombok.Data;

import java.util.List;

@Data
public class MovieListDto {
    private Long id;

    private String title;

    private List<VodDetailDto> vodDetailDtos;

    private String posterUrl;

    private Double voteAverage;

    private boolean searchResult;
}
