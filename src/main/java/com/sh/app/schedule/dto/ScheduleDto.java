package com.sh.app.schedule.dto;

import com.sh.app.movie.entity.Movie;
import com.sh.app.theater.dto.TheaterDto;
import lombok.Data;

@Data
public class ScheduleDto {

    private long id;

    private TheaterDto theater;

    private Movie movie;

    private String schDate;

    private String time;
}
