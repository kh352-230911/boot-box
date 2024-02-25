package com.sh.app.reservationSeat.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReservationSeatDto2 {
    private Long id;
    private String resId; //table의 res_id;
    private Long seatId;//table의 seat_id;
}