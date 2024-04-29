package com.sh.app.schedule.dto;



//0426 jin - 지점관리자 - 지점 상영 영화로 스케쥴 확인한 후 data 담는 dto

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SearchMovieScheduleDto
{
    private Long id; //스케쥴 아이디
    private Long theaterId; //상영관 아이디
    private Long movieId; //영화 아이디
    private String name; //상영관 이름
    private LocalDate schDate; //상영일자
    private LocalDateTime time;//상영시간
}
