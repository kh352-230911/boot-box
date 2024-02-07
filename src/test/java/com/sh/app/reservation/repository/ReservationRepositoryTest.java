package com.sh.app.reservation.repository;

import com.sh.app.reservation.entity.Reservation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
// pending - 예매중, confirm - 예매완료

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReservationRepositoryTest {

    @Autowired
    ReservationRepository reservationRepository;

    @DisplayName("예매 조회")
    @Test
    @Transactional
    void test1() {
        // given
        insertReservationData();

        // when
        List<Reservation> reservations = reservationRepository.findAll();

        // then
        assertThat(reservations).isNotNull();
        assertThat(reservations).hasSize(2);
        System.out.println("조회된값 : " + reservations);
    }

    @DisplayName("예매 추가")
    @Test
    @Transactional
    void test2() {
        // given
        Reservation reservation = Reservation.builder()
                .id(3L)
                .memberId(3L)
                .scheduleId(3L)
                .status("pending")
                .build();

        // when
        reservationRepository.save(reservation);
        assertThat(reservation.getId()).isNotNull().isNotZero();

        // then
        Reservation reservation1 = reservationRepository.findById(reservation.getId()).orElse(null);
        assertThat(reservation1)
                .isNotNull()
                .satisfies((_reservation -> {
                    assertThat(_reservation.getId()).isNotNull();
                    assertThat(_reservation.getMemberId()).isNotNull();
                    assertThat(_reservation.getScheduleId()).isNotNull();
                    assertThat(_reservation.getStatus()).isNotNull();
                }));
        System.out.println("추가된값 : " + reservation1);
    }

    // 예매변경하려면 취소 후 재예매로 가능

    @DisplayName("예매 취소")
    @Test
    @Transactional
    void test4() {
        // given
        Reservation reservation = Reservation.builder()
                .id(1L)
                .memberId(1L)
                .scheduleId(1L)
                .status("confirm")
                .build();
        reservationRepository.save(reservation);
        System.out.println("저장된값 : " + reservation);

        // when
        reservationRepository.delete(reservation);

        // then
        Reservation deleteReservation = reservationRepository.findById(reservation.getId()).orElse(null);
        assertThat(deleteReservation).isNull();
        System.out.println("삭제된값 : " + deleteReservation);
    }

    // 임의데이터
    public void insertReservationData() {
        Reservation reservation1 = Reservation.builder()
                .id(1L)
                .memberId(1L)
                .scheduleId(1L)
                .status("confirm")
                .build();
        reservationRepository.save(reservation1);

        Reservation reservation2 = Reservation.builder()
                .id(2L)
                .memberId(2L)
                .scheduleId(2L)
                .status("confirm")
                .build();
        reservationRepository.save(reservation2);
    }
}
