package com.sh.app.theater.dto;

import com.sh.app.cinema.entity.Cinema;
import com.sh.app.schedule.dto.ScheduleDTO;
import com.sh.app.schedule.entity.Schedule;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TheaterDTO {
    private Long id;

    private Cinema cinema;

    private String name;

    private int seat;

    private List<ScheduleDTO> schedules = new ArrayList<>();
}
