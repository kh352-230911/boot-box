package com.sh.app.member.dto;

import com.sh.app.reservation.dto.ReservationInfoDto;
import com.sh.app.reservation.entity.Reservation;
import com.sh.app.schedule.dto.ScheduleInfomDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberReservationDto {
    private Long id;
    private String name;
    private Set<ReservationInfoDto> reservations;
}
