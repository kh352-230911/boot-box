package com.sh.app.schedule.dto;

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
