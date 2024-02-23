package com.sh.app.schedule.dto;

import com.sh.app.movie.entity.Movie;
import com.sh.app.theater.dto.TheaterDto;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ScheduleListDto {
    private long id;
    private long theaterId;
    private long movieId;
    private LocalDate schDate;
    private LocalDateTime time;
}
