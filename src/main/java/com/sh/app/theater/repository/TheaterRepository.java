package com.sh.app.theater.repository;

import com.sh.app.theater.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheaterRepository extends JpaRepository<Theater, Long> {

    List<Theater> findByCinemaId(Long cinemaId);
}
