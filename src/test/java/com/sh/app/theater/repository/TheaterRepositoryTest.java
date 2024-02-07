package com.sh.app.theater.repository;


import com.sh.app.theater.entity.Theater;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TheaterRepositoryTest {
    @Autowired
    TheaterRepository theaterRepository;

    @DisplayName("극장 전체조회")
    @Test
    void test1() {
        // given
        insertTheaterData();
        // when
        List<Theater> schedules = theaterRepository.findAll();
        System.out.println(schedules);
        // then
        assertThat(schedules)
                .isNotEmpty()
                .hasSize(schedules.size());
    }

    private void insertTheaterData() {
        List<Theater> theaters = Arrays.asList(
                Theater.builder()
                        .cinemaId(1L)
                        .name("강남시네마")
                        .seat(40)
                        .build(),
                Theater.builder()
                        .cinemaId(2L)
                        .name("역삼 네모극장")
                        .seat(60)
                        .build()
        );
        theaters.forEach(theaterRepository::save);
    }
}
