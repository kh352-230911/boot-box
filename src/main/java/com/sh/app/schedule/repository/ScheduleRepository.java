package com.sh.app.schedule.repository;

import com.sh.app.schedule.dto.IScheduleInfoDto;
import com.sh.app.schedule.dto.ScheduleInfoDto;
import com.sh.app.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByTheaterId(Long theaterId);


    /**
     * <pre>
     * interface기반 projection방식
     * - IScheduleInfoDto
     *
     * - (주의사항) 프로젝션시 underscore to camelcase 자동변환이 지원되지 않으므로 별칭사용 필수
     * <pre>
     */
    @Query(value = """
        SELECT
            s.sch_date AS schDate,
            c.region_cinema AS regionCinema,
            m.title AS movieTitle,
            m.running_time AS runningTime,
            t.name AS theaterName,
            s.time AS startTime,
            (t.seat - (SELECT COUNT(*)
                       FROM reservation_seat rs
                       WHERE rs.res_id IN (SELECT r.id
                                           FROM reservation r
                                           WHERE r.schedule_id = s.id))) AS remainingSeats
        FROM cinema c
        JOIN theater t ON t.cinema_id = c.id
        JOIN schedule s ON s.theater_id = t.id
        JOIN movie_list ml ON ml.cinema_id = c.id
        JOIN movie m ON m.id = ml.movie_id AND m.id = s.movie_id
        WHERE c.id = :id AND s.sch_date = :schDate """, nativeQuery = true)
    List<IScheduleInfoDto> findScheduleDetailsByDateAndCinemaId(@Param("id") Long id,
                                                                @Param("schDate") LocalDate schDate);
}
