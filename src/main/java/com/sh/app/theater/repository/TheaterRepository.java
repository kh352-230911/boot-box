package com.sh.app.theater.repository;

import com.sh.app.theater.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TheaterRepository extends JpaRepository<Theater, Long> {

    @Query("from Theater t join fetch t.cinema c join fetch t.schedules s where c.id = :cinemaId order by t.id")
    List<Theater> findByCinemaId(Long cinemaId);


}
