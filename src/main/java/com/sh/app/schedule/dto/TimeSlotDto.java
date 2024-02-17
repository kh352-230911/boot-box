package com.sh.app.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlotDto { // 상영시간 그룹화 Dto
    private LocalDateTime startTime;
    private Integer remainingSeats;
}
