package com.sh.app.theater.repository;


import com.sh.app.cinema.entity.Cinema;
import com.sh.app.cinema.repository.CinemaRepository;
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
    @Autowired
    CinemaRepository cinemaRepository;

    @DisplayName("극장 전체조회")
    @Test
    void test1() {
        // given
        insertTheaterData();
        // when
        List<Theater> theaters = theaterRepository.findAll();
        System.out.println(theaters);
        // then
        assertThat(theaters)
                .isNotEmpty()
                .hasSize(theaters.size());
    }

    private void insertTheaterData() {

        Cinema cinema = Cinema.builder()
                .region_cinema("영등포점")
                .theater_number(10)
                .address("서울특별시 영등포구 영등포동")
                .location_lo(1234)
                .location_la(1234)
                .phone("02-1111-1234")
                .build();
        cinemaRepository.save(cinema);
        Cinema cinema2 = Cinema.builder()
                .region_cinema("관악점")
                .theater_number(10)
                .address("서울특별시 관악구 관악동")
                .location_lo(1234)
                .location_la(1234)
                .phone("02-1234-1234")
                .build();
        cinemaRepository.save(cinema2);
        Theater theater = Theater.builder()
                        .cinema(cinema)
                        .name("1관")
                        .seat(60)
                        .build();
        theater.setCinema(cinema);
        Theater theater2 = Theater.builder()
                        .cinema(cinema)
                        .name("2관")
                        .seat(60)
                        .build();
        theater2.setCinema(cinema2);
        theaterRepository.save(theater);
        theaterRepository.save(theater2);
    }
}
