package com.sh.app.schedule.dto;

import com.sh.app.movie.dto.MovieInfoDto;
import com.sh.app.theater.dto.TheaterInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleInfomDto {
    private LocalDate date;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private TheaterInfoDto theaters;

    private MovieInfoDto movie;

    // 상영 종료 시간을 계산하는 메소드를 추가합니다.
    public void calculateEndTime() {
        if (this.movie != null && this.movie.getRuntime() != null) {
            this.endTime = this.startTime.plusMinutes(this.movie.getRuntime());
        }
    }
}
