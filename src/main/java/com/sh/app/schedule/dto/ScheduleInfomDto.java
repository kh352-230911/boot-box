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

    private LocalDateTime time;

    private TheaterInfoDto theaters;

    private MovieInfoDto movie;
}
