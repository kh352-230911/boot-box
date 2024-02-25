package com.sh.app.pay.entity;

import com.sh.app.common.Status;
import com.sh.app.reservation.entity.Reservation;
import jakarta.persistence.*;
import lombok.*;

/**
 * 0207 hyejin
 * db 주문 결제 테이블과 매칭되는 entity
 */

@Entity
//Table annotaion 엔티티와 매핑할 테이블 지정. 생략시 매핑한 엔티티 이름을 테이블 이름으로 지정함.
@Table(name = "order_pay")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"reservation"})
@ToString(exclude = "reservation")
//서브
public class OrderPay {
    @Id
    @Column
    private String id; //pk , order+getTime조합

//    @Column(nullable = false,name="reservation_id")
//    private String reservationId; //참조할 예약id

    @Column(nullable = false,name="member_id")
    private Long memberId; //참조할 user id

    private String imp; //가맹점 식별코드

    private String inicis; //지원 pg사

    //@Column(nullable = false,name="reservation_amount")
    private String reservationAmount; //결제 방식

    private int price; //총 결제 금액
 
    private String phone; //핸드폰 번호

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status; //결제 상태

    @OneToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name="reservation_id")
    private Reservation reservation;

    /**
     * 0208 OrderPay 객체 생성시 reservation 객체 set 해보기
     * orderpay는 reservation이 만들어지고 나서 데이터를 set해야함
     * 1.reservation
     * 2.orderpay
     */
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
