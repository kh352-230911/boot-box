package com.sh.app.schedule.dto;

import com.sh.app.movie.entity.Movie;
import com.sh.app.theater.dto.TheaterDTO;
import com.sh.app.theater.entity.Theater;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class ScheduleDTO {

    private long id;

    private TheaterDTO theater;

    private Movie movie;

    private String schDate;

    private String time;
}
