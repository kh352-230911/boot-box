package com.sh.app.reservation.repository;

import com.sh.app.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *  <Entity객체클래스,해당 객체클래스의 pk DataType>
 *
 *     예약번호가 number일 경우 식별이 어려워
 *     예약 번호만 varchar2 형식을 정하기로 했습니다.
 *     BOX + 랜덤숫자5개
 */
public interface ReservationRepository extends JpaRepository<Reservation, String> {
//    @Query("select r from Reservation r join fetch r.schedule s where r.memberId = :id and s.time < current_timestamp")
// @Query("SELECT r FROM Reservation r JOIN FETCH r.schedule s JOIN FETCH s.movie JOIN FETCH r.seats JOIN FETCH s.theater t JOIN FETCH
// t.cinema JOIN FETCH r.review WHERE r.memberId = :id AND s.time < CURRENT_TIMESTAMP")  // schema 에서 reservation 테이블에 review_id 컬럼 추가한 후 사용
    @Query("select r from Reservation r join fetch r.schedule s join fetch s.movie join fetch r.seats join fetch s.theater t join fetch " +
            "t.cinema where r.memberId = :id and s.time < current_timestamp")
    List<Reservation> findPastReservationsById(Long id);

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.schedule.movie.id = :id")
    long countByMovieId(Long id);

    @Query("SELECT r.schedule.movie.id AS movieId, COUNT(r.id) AS reservationCount FROM Reservation r GROUP BY r.schedule.movie.id")
    List<Object[]> findReservationCountByMovieId();

    //Reservation findById(Long id);
    //@Query("from Reservation r join fetch r.orderPay where r.id = : id")
    //Reservation findById(String id);
}

