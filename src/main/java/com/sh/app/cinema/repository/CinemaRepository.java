package com.sh.app.cinema.repository;

import com.sh.app.cinema.dto.CinemaProjection;
import com.sh.app.cinema.entity.Cinema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CinemaRepository extends JpaRepository<Cinema, Long>{

    @Query("from Cinema c where c.region_cinema like '%' || :name || '%'")
    List<Cinema> findByNameContaining(String name);


    @Query("from Cinema c left join fetch c.location")
    Page<Cinema> findAll(Pageable pageable);

    @Query(value = "SELECT new CinemaProjection(s.schDate, c.regionCinema, m.title, t.name, s.time, (t.seat - (SELECT COUNT(rs) FROM ReservationSeat rs WHERE rs.seat.id IN (SELECT seat.id FROM Seat seat WHERE seat.schedule.id = s.id)))) " +
            "FROM Schedule s " +
            "JOIN s.cinema c " +
            "JOIN s.movie m " +
            "JOIN s.theater t " +
            "WHERE s.schDate = :schDate AND c.regionCinema = :regionCinema AND m.title = :title", nativeQuery = true)
    List<CinemaProjection> findCinemaDetails(LocalDate schDate, String regionCinema, String title);


}
