package com.sh.app.seat.repository;

import com.sh.app.seat.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Query("from Seat s order by s.id desc")
    List<Seat> findByOrderByDesc();

    @Query("from Seat s where s.name like :name")
    List<Seat> findByNameLike(@Param("name") String name);

    @Query("from Seat s where s.name in :names")
    List<Seat> findByNameIn(List<String> names);
}
