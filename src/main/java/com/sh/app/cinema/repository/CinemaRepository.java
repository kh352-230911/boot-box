package com.sh.app.cinema.repository;

import com.sh.app.cinema.entity.Cinema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CinemaRepository extends JpaRepository<Cinema, Long>{

    @Query("from Cinema c where c.region_cinema like '%' || :name || '%'")
    List<Cinema> findByNameContaining(String name);


    @Query("from Cinema c left join fetch c.location")
    Page<Cinema> findAll(Pageable pageable);

    @Query(value = "SELECT " +
            "S.sch_date AS schDate, " +
            "C.region_cinema AS regionCinema, " +
            "M.title AS title, " +
            "T.name AS theaterName, " +
            "S.time AS startTime, " +
            "(T.seat_count - (SELECT COUNT(*) FROM reservation_seat RS WHERE RS.seat_id IN (SELECT ST.id FROM seat ST WHERE ST.theater_id = T.id AND S.id = RS.schedule_id))) AS remainingSeats " +
            "FROM schedule S " +
            "JOIN cinema C ON S.cinema_id = C.id " +
            "JOIN movie M ON S.movie_id = M.id " +
            "JOIN theater T ON S.theater_id = T.id " +
            "WHERE S.sch_date = :schDate AND C.region_cinema = :regionCinema", nativeQuery = true)
    List<Object[]> findCinemaDetailsNative(@Param("schDate") LocalDate schDate,
                                           @Param("regionCinema") String regionCinema);

}
