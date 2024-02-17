package com.sh.app.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheaterScheduleDto { // 상영관 그룹화 Dto
    private String theaterName;
    private List<TimeSlotDto> times;
}
