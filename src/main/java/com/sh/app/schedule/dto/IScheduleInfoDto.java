package com.sh.app.schedule.dto;

import java.time.LocalDateTime;

public interface IScheduleInfoDto {
    LocalDateTime getSchDate();  // 스케줄 테이블 날짜
    String getRegionCinema(); // 극장지점명 ex) 강남점
    String getMovieTitle(); // 영화제목
    String getRunningTime(); // 총 상영시간 ex) 120분
    String getTheaterName(); // 상영관 이름 , 1관 2관..
    LocalDateTime getStartTime(); // 영화 시작시간
    Integer getRemainingSeats(); // 남은 좌석

}
