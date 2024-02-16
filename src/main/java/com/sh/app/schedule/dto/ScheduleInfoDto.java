package com.sh.app.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleInfoDto {
    private LocalDateTime schDate;  // 스케줄 테이블 날짜
    private String regionCinema; // 극장지점명 ex) 강남점
    private String movieTitle; // 영화제목
    private String runningTime; // 총 상영시간 ex) 120분
    private String theaterName; // 상영관 이름 , 1관 2관..
    private LocalDateTime time; // 영화 시작시간
    private Integer remainingSeats; // 남은 좌석
}
