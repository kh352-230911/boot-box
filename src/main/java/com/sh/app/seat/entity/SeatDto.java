package com.sh.app.seat.entity;


import lombok.Data;


/**
 * 0221
 * --from 대상 변경 reservation_seat -> seat
 * select s.*
 * from seat s
 * join reservation_seat rs on s.id = rs.seat_id
 * join reservation r on rs.res_id = r.id
 * WHERE r.schedule_id = 70;
 *
 *
 */
@Data
public class SeatDto {

    private Long id;
    private String name;

}