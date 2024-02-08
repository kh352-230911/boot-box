package com.sh.app.reservation.repository;

import com.sh.app.common.Status;
import com.sh.app.pay.entity.OrderPay;
import com.sh.app.pay.repository.OrderPayRepository;
import com.sh.app.reservation.entity.Reservation;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


/**
 * 0206 hyejin
 * 예매 관련 db test
 *
 * 1.예매 시작 insert
 * 2.예매 완료시 status 변경
 * 3.예매 취소 delete ->상태값 yet
 *
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //기본 db 교체 하지 않음.
@Transactional(propagation = Propagation.NOT_SUPPORTED)//service단에 쓰는 어노테이션과 같음
class ReservationRepositoryTest {
    
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    OrderPayRepository orderPayRepository;

    //영화 예매 테이블 : 주문 결제 테이블 [one to one]
    @DisplayName("예매 시작 시, 예매 정보와 주문결제 테이블도 함께 insert가 되어야 한다.")
    @Test
    void test1()
    {
        //solution : OrderPay객체 생성시 reservation객체 set해보기

        //given
        //1.영화 예매 테이블 insert(예약 시작)
        Reservation reservation = Reservation.builder()
                .id("BOX99999") //예매 고유 id pk Long이 아니라 String 입니다..
                .memberId(1234l) //멤버 id
                .scheduleId(1L) //상영 id
                .status(Status.PENDING) //pending[보류중]상태로 insert함
                .build();

        System.out.println("===================1=====================");
        System.out.println(reservation);

        reservationRepository.save(reservation);


        System.out.println("===================2=====================");
        //2.주문 결제 테이블(insert)
        OrderPay orderPay = OrderPay.builder()
                .id("order"+new Date().getTime())//order+시간으로 겹치지 않는 id를 생성한다.
                //.reservationId("BOX12345") //예매 고유 id
                .memberId(1234L)//나중에 findById로 member.getId() 대체
                .imp("kh1010") //가맹점 식별코드
                .inicis("inicis")
                .reservationAmount("card")
                .price(12000)
                .phone("01012345678")
                .status(Status.PENDING)
                .build();

        //set
        System.out.println("===================set test=====================");
        orderPay.setReservation(reservation);

        orderPayRepository.save(orderPay);

        System.out.println(reservation);
        System.out.println(orderPay);

        //then
        assertThat(reservation.getId()).isNotNull();
        assertThat(orderPay.getId()).isNotNull();

    }

    @Disabled
    @DisplayName("결제 성공 가정 -> 예약 한 건 완료(Confirm)로 update")
    @Test
    @Transactional
    void test2()
    {
        //given
        //1.영화 예매 테이블 insert(예약 시작)
        Reservation reservation = Reservation.builder()
                .id("BOX12345") //예매 고유 id pk (VARCHAR2)
                .memberId(999L) //멤버 id
                .scheduleId(1L) //상영 id
                .status(Status.PENDING) //pending[보류중]상태로 insert함
                .build();

        reservationRepository.save(reservation); //insert or update

        //2.주문 결제 테이블(insert)
        OrderPay orderPay = OrderPay.builder()
                .id("order"+new Date().getTime())//order+시간으로 겹치지 않는 id를 생성한다.
                //.reservationId("BOX12345") //예매 고유 id
                .memberId(777L)//나중에 findById로 member.getId() 대체
                .imp("kh1010") //가맹점 식별코드
                .inicis("inicis")
                .reservationAmount("card")
                .price(12000)
                .phone("01012345678")
                .status(Status.PENDING)
                .build();

        orderPayRepository.save(orderPay);

        //when
//        Reservation reservation2 = reservationRepository.findById(20240207L);
//        System.out.println("20240207L 아이디를 갖고있는 예약내역 조회:"+reservation2);

    }

    @Disabled
    @DisplayName("결제 실패 가정 - 예약 한 건 보류")
    @Test
    @Transactional
    void test3()
    {

    }

}