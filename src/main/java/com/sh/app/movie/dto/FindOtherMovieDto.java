package com.sh.app.movie.dto;


import lombok.Data;

//0424 지점관리자가 본인 지점에 새로 추가할 영화를 찾을 때 사용됨
@Data
public class FindOtherMovieDto
{
    private long id;
    private String title;
    private String posterUrl;
}
