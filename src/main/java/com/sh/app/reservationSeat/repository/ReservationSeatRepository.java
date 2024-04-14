package com.sh.app.reservationSeat.repository;

import com.sh.app.reservationSeat.entity.ReservationSeat;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationSeatRepository extends JpaRepository<ReservationSeat,Long> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM reservation_seat WHERE res_id = :reservationId", nativeQuery = true)
    void deleteByReservationId(@Param("reservationId") String reservationId);

}
