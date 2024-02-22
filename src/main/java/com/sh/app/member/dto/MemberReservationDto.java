package com.sh.app.member.dto;

import com.sh.app.reservation.entity.Reservation;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class MemberReservationDto {
    private Long id;
    private Set<Reservation> reservations = new HashSet<>();
}
