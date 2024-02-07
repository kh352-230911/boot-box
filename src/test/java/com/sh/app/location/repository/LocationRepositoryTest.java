package com.sh.app.location.repository;

import com.sh.app.location.entity.Location;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LocationRepositoryTest {

    @Autowired
    LocationRepository locationRepository;

    @DisplayName("지역 전체 조회")
    @Test
    void test1() {
        // given
        insertLocationData();

        // when
        List<Location> locations = locationRepository.findAll();
        System.out.println(locations);

        // then
        assertThat(locations)
                .isNotNull()
                .isNotEmpty();
    }

    @DisplayName("지역을 추가할 수 있다.")
    @Test
    void test2() {
        // given
        Location location = Location.builder()
                .location_name("경북")
                .build();

        // when
        locationRepository.save(location);
        System.out.println(location);

        // then
        assertThat(location.getId()).isNotNull().isNotZero();
    }

    @DisplayName("지역 한개를 삭제할 수 있다.")
    @Test
    void test3() {
        // given
        Location location = Location.builder()
                .location_name("경남").build();
        locationRepository.save(location);
        System.out.println(location);

        // when, 위치 삭제
        locationRepository.deleteById(location.getId());

        // then 삭제 확인
        Optional<Location> optLocation = locationRepository.findById(location.getId());
        Location location2 = optLocation.orElse(null);
        assertThat(location2).isNull();
    }

    @DisplayName("이미 있는 지역 한개를 수정할 수 있다.")
    @Test
    void test4() {
        // given
        Location location = Location.builder()
                                    .location_name("서울")
                                    .build();
        locationRepository.save(location);

        // when
        String newLocalname = "서울특별시";
        location.setLocation_name(newLocalname);
        locationRepository.save(location); // id가 존재하여 update 처리

        // then, optional 객체로 반환
        Optional<Location> maybeLocation2 = locationRepository.findById(location.getId());
        Location location2 = maybeLocation2.orElse(null);
        System.out.println(location2);

        assertThat(location2)
                .isNotNull()
                .satisfies((_location) -> {
                   assertThat(_location.getId()).isEqualTo(location.getId());
                   assertThat(_location.getLocation_name()).isEqualTo(newLocalname);
                });
    }

    private void insertLocationData() {
        List<Location> locations = Arrays.asList(
                Location.builder()
                        .location_name("서울")
                        .build(),
                Location.builder()
                        .location_name("경기")
                        .build(),
                Location.builder()
                        .location_name("인천")
                        .build(),
                Location.builder()
                        .location_name("강원")
                        .build(),
                Location.builder()
                        .location_name("대전/충청")
                        .build(),
                Location.builder()
                        .location_name("대구")
                        .build(),
                Location.builder()
                        .location_name("부산/울산")
                        .build(),
                Location.builder()
                        .location_name("경상")
                        .build(),
                Location.builder()
                        .location_name("광주/전라/제주")
                        .build()
        );
        locations.forEach(locationRepository::save);
    }


}