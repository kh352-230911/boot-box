package com.sh.app.schedule.repository;

import com.sh.app.movie.entity.Movie;
import com.sh.app.schedule.entity.Schedule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ScheduleRepositoryTest {
    @Autowired
    ScheduleRepository scheduleRepository;

    @DisplayName("상영일정 전체조회")
    @Test
    void test1() {
        // given
        insertScheduleData();
        // when
        List<Schedule> schedules = scheduleRepository.findAll();
        System.out.println(schedules);
        // then
    }

    private void insertScheduleData() {
        List<Schedule> schedules = Arrays.asList(
                Schedule.builder()
                        .movieId(1L)
                        .theaterId(1L)
                        .schDate("2024.01.31")
                        .time("1시30분")
                        .build()
        );
        schedules.forEach(scheduleRepository::save);
    }
}
