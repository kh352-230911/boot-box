package com.sh.app.reservationSeat.repository;

import com.sh.app.reservationSeat.entity.ReservationSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationSeatRepository extends JpaRepository<ReservationSeat,Long> {
}
