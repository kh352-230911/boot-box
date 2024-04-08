package com.sh.app.schedule.repository;

import com.sh.app.cinema.entity.Cinema;
import com.sh.app.cinema.repository.CinemaRepository;
import com.sh.app.schedule.entity.Schedule;
import com.sh.app.theater.entity.Theater;
import com.sh.app.theater.repository.TheaterRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ScheduleRepositoryTest {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    MovieDataRepository movieDataRepository;
    @Autowired
    CinemaRepository cinemaRepository;

    @DisplayName("상영일정 전체조회")
    @Test
    void test1() {
        // given
        insertScheduleData();
        // when
        List<Schedule> schedules = scheduleRepository.findAll();
        System.out.println(schedules);
        // then
        assertThat(schedules)
                .isNotEmpty()
                .hasSize(schedules.size());
    }

    private void insertScheduleData() {
        MovieData movieData = MovieData.builder().title("웡카").filmRatings(Rating.ALL).releaseDate("2024.01.31").runningTime(116)
                .trailer("https://www.kmdb.or.kr/trailer/trailerPlayPop?pFileNm=MK060560_P02.mp4")
                .poster("http://file.koreafilm.or.kr/thm/02/99/18/30/tn_DPF028589.jpg")
                .director("폴 킹").actor("티모시 샬라메").summary("세상에서 가장 달콤한 여정 좋은 일은 모두 꿈에서부터 시작된다!")
                .advanceReservation(98).build();
        MovieData movieData2 = MovieData.builder().title("시민덕희").filmRatings(Rating.FIFTEEN).releaseDate("2024.01.24").runningTime(114)
                .trailer("https://www.kmdb.or.kr/trailer/trailerPlayPop?pFileNm=MK060515_P02.mp4")
                .poster("http://file.koreafilm.or.kr/thm/02/99/18/28/tn_DPK021526.jpg")
                .director("박영주").actor("라미란").summary("내 돈을 사기 친 그 놈이 구조 요청을 해왔다!")
                .advanceReservation(99).build();
        movieDataRepository.save(movieData);
        movieDataRepository.save(movieData2);
        Cinema cinema = Cinema.builder()
                .region_cinema("영등포점")
                .theater_number(10)
                .address("서울특별시 영등포구 영등포동")
                .location_lo(1234)
                .location_la(1234)
                .phone("02-1111-1234")
                .build();
        Cinema cinema2 = Cinema.builder()
                .region_cinema("관악점")
                .theater_number(10)
                .address("서울특별시 관악구 관악동")
                .location_lo(1234)
                .location_la(1234)
                .phone("02-1234-1234")
                .build();
        cinemaRepository.save(cinema);
        cinemaRepository.save(cinema2);
        Theater theater = Theater.builder()
                .cinema(cinema)
                .name("1관")
                .seat(60)
                .build();
        Theater theater2 = Theater.builder()
                .cinema(cinema2)
                .name("2관")
                .seat(60)
                .build();
        theater.setCinema(cinema);
        theater2.setCinema(cinema2);
        theaterRepository.save(theater);
        theaterRepository.save(theater2);
        Schedule schedule1 =
                Schedule.builder()
                        .movieData(movieData)
                        .schDate("2024.01.31")
                        .theater(theater)
                        .time("120분")
                        .build();
        Schedule schedule2 =
                Schedule.builder()
                        .movieData(movieData)
                        .schDate("2024.02.31")
                        .theater(theater)
                        .time("180분")
                        .build();
        schedule1.setTheater(theater);
        schedule1.setMovieData(movieData);
        schedule2.setTheater(theater2);
        schedule2.setMovieData(movieData2);
        scheduleRepository.save(schedule1);
        scheduleRepository.save(schedule2);
    }
}
