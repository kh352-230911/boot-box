package com.sh.app.theater.dto;

import com.sh.app.cinema.entity.Cinema;
import com.sh.app.schedule.dto.ScheduleDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TheaterDto {
    private Long id;

    private Cinema cinema;

    private String name;

    private int seat;

    private List<ScheduleDto> schedules = new ArrayList<>();
}
