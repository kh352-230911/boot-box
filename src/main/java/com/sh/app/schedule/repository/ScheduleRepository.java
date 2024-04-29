package com.sh.app.schedule.repository;

import com.sh.app.schedule.dto.IScheduleInfoDto;
import com.sh.app.schedule.dto.ScheduleCookieDto;
import com.sh.app.schedule.dto.ScheduleInfoDto;
import com.sh.app.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("from Schedule s join fetch s.theater t where t.id = :theaterId")
    List<Schedule> findByTheaterId(Long theaterId);


    /**
     * <pre>
     * interface기반 projection방식
     * - IScheduleInfoDto
     *
     * - (주의사항) 프로젝션시 underscore to camelcase 자동변환이 지원되지 않으므로 별칭사용 필수
     * <pre>
     */
    
    
    //1.특정 영화관지점(강남점,왕십리점..)의 모든 영화와 스케쥴을 갖고오는 경우 (원하는 날짜에 맞춰서)
    @Query(value = """
        SELECT
            m.id AS movieId,
            c.id AS cinemaId,
            s.id AS schId,
            s.sch_date AS schDate,
            c.region_cinema AS regionCinema,
            m.film_ratings AS filmRatings,
            m.title AS movieTitle,
            m.runtime AS runningTime,
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
        WHERE (c.id = :id AND s.sch_date = :schDate AND s.approve='Y') AND s.time > CURRENT_TIMESTAMP ORDER BY t.id,s.time""", nativeQuery = true)
    List<IScheduleInfoDto> findScheduleDetailsByDateAndCinemaId(@Param("id") Long id,
                                                                @Param("schDate") LocalDate schDate);

    //  WHERE (c.id = :id AND s.sch_date = :schDate) AND s.time > CURRENT_TIMESTAMP """, nativeQuery = true)


    //2.영화 예매페이지에서 원하는 영화를 선택해서 스케쥴을 확인할 경우(1에서 where 조건에 영화 id가 한개 더 추가됨)
    @Query(value = """
        SELECT
            m.id AS movieId,
            c.id AS cinemaId,
            s.id AS schId,
            s.sch_date AS schDate,
            c.region_cinema AS regionCinema,
            m.title AS movieTitle,
            m.runtime AS runningTime,
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
        WHERE (m.id= :movieId AND c.id = :cinemaId AND s.sch_date = :schDate AND s.approve='Y') AND s.time > CURRENT_TIMESTAMP ORDER BY t.id,s.time""", nativeQuery = true)
    List<IScheduleInfoDto> findScheduleDetailsByDateAndCinemaId_2(Long movieId, Long cinemaId, LocalDate schDate);

    Long countByMovieId(Long movieId);

    @Query("SELECT s.movie.id AS movieId, COUNT(s.id) AS scheduleCount FROM Schedule s GROUP BY s.movie.id")
    List<Object[]> findScheduleCountByMovieId();


    //해당 영화, 지점으로 현재기준으로 미래인 상영스케쥴 조회(단, 승인된 스케쥴이여야 한다)
    @Query("SELECT s FROM Schedule s JOIN s.theater t WHERE t.cinema.id = :cinemaId AND s.movie.id = :movieId AND s.approve='Y' AND s.time > CURRENT_TIMESTAMP ")
    List<Schedule> searchMovieSchedule(Long cinemaId, Long movieId);


    //0428 극장에서 상영일정 클릭해서 바로 예매페이지 넘어가는 경우! 상영일정id 하나만으로 조회 시도
//    @Query("SELECT s FROM Schedule s JOIN s.theater t JOIN  t.cinema c JOIN  s.movie m WHERE s.id = :schId")
//    List<Schedule> loadMovieInfoByCookie(Long schId);

    @Query("SELECT s.id, t.name, c.region_cinema, m.title,m.posterUrl, m.runtime,s.schDate FROM Schedule s JOIN s.theater t JOIN t.cinema c JOIN s.movie m WHERE s.id = :schId")
    List<Object[]> loadMovieInfoByCookie(Long schId);
}
