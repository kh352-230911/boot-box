package com.sh.app.pay.dto;


import com.sh.app.common.Status;
import lombok.Data;

@Data
public class OrderPayDto {
    private String id; //order~
    private String reservationId; //예약 아이디
    private Long memberId; //회원 아이디[시퀀스]
    private String imp; //가맹점 식별코드
    private String inicis; //이니시스  지원 pg사
    private String reservationAmount; //결제 방식
    private int price; //결제금액
    private String phone; //회원 연락처
    private Status status;//상태

}
