package com.sh.app.schedule.dto;


import com.sh.app.movie.entity.Movie;
import com.sh.app.theater.entity.Theater;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * jin
 * 극장에서 예매하는 경우 원하는 정보를 가져오기 위한 dto (cookie로 상영 스케쥴 id만 가져와서 이걸로 스케쥴 테이블 조인 조회함)
 * 다른 dto를 사용해도 될 것 같긴한데 일단 용도 구별하기 위해 하나 더 만들었습니다.
 *
 * */
@Data
public class ScheduleCookieDto
{


    private Long id;
    private String title; //영화명
    private String posterUrl;//포스터주소
    private String regionCinema; //지점명(강남점 등..)
    private String name;//theater name( 2관,,)
    private LocalDate schDate; //상영 날짜
    private Integer runtime; //상영시간
}
