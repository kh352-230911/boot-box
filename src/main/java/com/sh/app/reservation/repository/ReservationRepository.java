package com.sh.app.reservation.repository;

import com.sh.app.reservation.dto.ReservationInfoDto;
import com.sh.app.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 *  <Entity객체클래스,해당 객체클래스의 pk DataType>
 *
 *     예약번호가 number일 경우 식별이 어려워
 *     예약 번호만 varchar2 형식을 정하기로 했습니다.
 *     BOX + 랜덤숫자5개
 */
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    @Query("select r from Reservation r join fetch r.schedule s where r.memberId = :id and s.time < current_timestamp")
    List<Reservation> findPastReservationsById(Long id);
    //Reservation findById(Long id);
    //@Query("from Reservation r join fetch r.orderPay where r.id = : id")
    //Reservation findById(String id);
}

