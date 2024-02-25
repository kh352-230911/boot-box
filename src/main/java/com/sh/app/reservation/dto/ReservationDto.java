package com.sh.app.reservation.dto;


import com.sh.app.common.Status;
import lombok.Data;

/**
 * 0223 Reservation dto
 * 1.예약 아이디  box12345
 * 2.회원 아이디 rhgPwls이 아닌 1
 * 3.상영일정 아이디
 * 4.예약 상태 -현재는 confirm으로 무조건 고정한다.
 */
@Data
public class ReservationDto {
    private String id;
    private Long memberId;
    private Long scheduleId;
    private Status status; //enum
}
