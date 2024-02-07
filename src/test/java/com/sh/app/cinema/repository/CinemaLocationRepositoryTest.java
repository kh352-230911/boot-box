package com.sh.app.cinema.repository;

import com.sh.app.cinema.entity.Cinema;
import com.sh.app.location.entity.Location;
import com.sh.app.location.repository.LocationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CinemaLocationRepositoryTest {

    @Autowired
    CinemaRepository cinemaRepository;
    @Autowired
    LocationRepository locationRepository;


    @DisplayName("지역 등록 후 극장 목록을 전체 조회할 수 있다.")
    @Test
    void test1() {
        // given
        insertCinemaData();

        // when
        List<Cinema> cinemas = cinemaRepository.findAll();
        System.out.println(cinemas);

        assertThat(cinemas)
                .isNotEmpty()
                .hasSize(cinemas.size())
                .containsOnly(
                        cinemas.get(0),
                        cinemas.get(1),
                        cinemas.get(2)
                );
    }

    @DisplayName("원하는 극장을 조회할 수 있다.")
    @Test
    void test2() {
        // given
        insertCinemaData();
        String name = "영등포";

        // when
        List<Cinema> cinemas = cinemaRepository.findByNameContaining(name);
        System.out.println(cinemas);

        // then
        assertThat(cinemas)
                .isNotEmpty()
                .hasSize(1)
                .allSatisfy(cinema -> {
                   assertThat(cinema.getRegion_cinema()).contains(name);
                });
    }

    @DisplayName("지역 등록 후 극장 조회하기 - 양방향 조회")
    @Test
    @Transactional
    void test3() {
        // given
        Location location1 = Location.builder()
                .location_name("서울")
                .build();
        locationRepository.save(location1);

        Cinema cinema1 = Cinema.builder()
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

        cinema1.setLocation(location1);
        cinema2.setLocation(location1);

        cinemaRepository.save(cinema1);
        cinemaRepository.save(cinema2);

        // when
        Cinema cinemaList = cinemaRepository.findById(cinema1.getId()).orElse(null);
        System.out.println(cinemaList);
        System.out.println(cinemaList.getLocation().getCinemas());

        // then
        assertThat(cinemaList)
                .isNotNull()
                .satisfies((cinema -> {
                    assertThat(cinema.getLocation()).isNotNull();
                    assertThat(cinema.getLocation().getId()).isEqualTo(location1.getId());
                    assertThat(cinema.getLocation().getLocation_name()).isEqualTo(location1.getLocation_name());
                    assertThat(cinema.getLocation().getCinemas()).contains(cinemaList);
                }));

        Location location2 = locationRepository.findById(location1.getId()).orElse(null);
        assertThat(location2)
                .isNotNull()
                .satisfies((location -> {
                    assertThat(location.getCinemas()).contains(cinema1, cinema2);
                }));

    }

    void insertCinemaData() {
        Location location1 = Location.builder()
                .location_name("서울")
                .build();
        Location location2 = Location.builder()
                .location_name("인천")
                .build();
        Location location3 = Location.builder()
                .location_name("경기")
                .build();

        Cinema cinema1 = Cinema.builder()
                .region_cinema("영등포점")
                .theater_number(10)
                .address("서울특별시 영등포구 영등포동")
                .location_lo(1234)
                .location_la(1234)
                .phone("02-1111-1234")
                .build();
        Cinema cinema2 = Cinema.builder()
                .region_cinema("계양점")
                .theater_number(10)
                .address("인천광역시 계양구 계양동")
                .location_lo(1234)
                .location_la(1234)
                .phone("032-1234-1234")
                .build();
        Cinema cinema3 = Cinema.builder()
                .region_cinema("수원광교점")
                .theater_number(10)
                .address("경기도 수원시 광교동")
                .location_lo(1234)
                .location_la(1234)
                .phone("031-1234-1234")
                .build();

        locationRepository.save(location1);
        locationRepository.save(location2);
        locationRepository.save(location3);

        // 극장에서 지역 id 외래키 setter
        cinema1.setLocation(location1);
        cinema2.setLocation(location2);
        cinema3.setLocation(location3);

        cinemaRepository.save(cinema1);
        cinemaRepository.save(cinema2);
        cinemaRepository.save(cinema3);

    }
}