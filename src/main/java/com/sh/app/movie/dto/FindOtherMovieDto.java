package com.sh.app.movie.dto;


import lombok.Data;

import java.time.LocalDate;

//0424 지점관리자가 본인 지점에 새로 추가할 영화를 찾을 때 사용됨
@Data
public class FindOtherMovieDto
{
    private Long id;
    private String title;
    private String posterUrl;
    //아래 3가지 필드 더 추가.(정보 제공용이라 필수 아님)
    private String filmRatings;
    private Integer runtime;
    private LocalDate releaseDate;
}
