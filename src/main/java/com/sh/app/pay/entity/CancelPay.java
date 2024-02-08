package com.sh.app.pay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 결제 취소 테이블과 1대1 매칭되는 entity class
 */

@Entity
//Table annotaion 엔티티와 매핑할 테이블 지정. 생략시 매핑한 엔티티 이름을 테이블 이름으로 지정함.
@Table(name = "cancel_pay")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CancelPay {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //시퀀스로 채번하는 pk

    @Column(nullable = false,name="member_id")
    private Long memberId; //회원 아이디(pk)를 fk로 참조한다.

    @Column(name="c_rerservation_pay")
    private String cReservationPay;//지불금액과 환불금액이 일치하는지.

    @Column(name="c_reservation_amount")
    private String cReservationAmount; //지불방식과 환불방식이 일치하는지.

}
