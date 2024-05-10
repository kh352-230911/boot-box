package com.sh.app.movie.dto;

import lombok.Data;



//0501 내 지점에서 상영하는 영화 정보 간단히 가져올때.
@Data
public class MyCinemaMovieDto
{
    private Long id;
    private String title;
}
