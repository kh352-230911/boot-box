package com.sh.app.reservation.dto;

import com.sh.app.review.dto.ReviewInfoDto;
import com.sh.app.schedule.dto.ScheduleInfomDto;
import com.sh.app.seat.dto.SeatInfoDto;
import com.sh.app.seat.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationInfoDto {
    private String id;

    private Set<SeatInfoDto> seats;

    private ScheduleInfomDto schedule;

    private ReviewInfoDto review;
}
