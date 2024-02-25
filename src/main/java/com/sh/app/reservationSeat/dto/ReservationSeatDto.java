package com.sh.app.reservationSeat.dto;


import com.sh.app.reservation.dto.ReservationDto;
import lombok.Data;

import java.util.List;

@Data
public class ReservationSeatDto {
    private Long id;
    private String resId; //table의 res_id;
    //private Long seatId;//table의 seat_id;
    private List<Long> seatId;
}
