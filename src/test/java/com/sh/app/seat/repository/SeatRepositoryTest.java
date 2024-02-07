package com.sh.app.seat.repository;

import com.sh.app.seat.entity.Seat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SeatRepositoryTest {

    @Autowired
    SeatRepository seatRepository;

    private List<Seat> seats;

    @DisplayName("좌석 전체 조회")
    @Test
    void test1() {
        // given
        insertSeatData();

        // when
        List<Seat> seats = seatRepository.findAll();
        System.out.println(seats);

        // then
        assertThat(seats)
                .isNotEmpty()
                .hasSize(this.seats.size())
                .containsOnly(
                        seats.get(0),
                        seats.get(1),
                        seats.get(2),
                        seats.get(3),
                        seats.get(4),
                        seats.get(5),
                        seats.get(6),
                        seats.get(7),
                        seats.get(8),
                        seats.get(9),
                        seats.get(10),
                        seats.get(11),
                        seats.get(12),
                        seats.get(13),
                        seats.get(14),
                        seats.get(15),
                        seats.get(16),
                        seats.get(17),
                        seats.get(18),
                        seats.get(19),
                        seats.get(20),
                        seats.get(21),
                        seats.get(22),
                        seats.get(23),
                        seats.get(24)
                );

    }

    @DisplayName("좌석 내역 order by 정렬")
    @Test
    void test2() {
        // given
        insertSeatData();
        Sort sort = Sort.by("id").descending();

        // when
        List<Seat> seats = seatRepository.findByOrderByDesc();
        System.out.println(seats);

        // then
        assertThat(seats)
                .isNotEmpty()
//                .isSorted()
                .isSortedAccordingTo(Collections.reverseOrder());
    }

    @DisplayName("좌석 A열 Like 조회")
    @Test
    void test3() {
        // given
        insertSeatData();
        String name = "A";

        // when
        List<Seat> seats = seatRepository.findByNameLike("%" + name + "%");
        System.out.println(seats);

        // then
        assertThat(seats)
                .isNotEmpty()
                .hasSize(5)
                .allSatisfy((seat -> {
                    assertThat(seat.getName()).contains(name);
                }));

    }

    @DisplayName("좌석 갯수 count")
    @Test
    void test4() {
        // given
        insertSeatData();

        // when
        long count = seatRepository.count();
        System.out.println(count);

        assertThat(count)
                .isEqualTo(seats.size());
    }


    @DisplayName("예약된 좌석을 조회할 수 있다. (특정 좌석 조회)")
    @Test
    void test5() {
        // given
        insertSeatData();
        List<String> names = Arrays.asList("A4", "A5", "B1", "B2");

        // when
        List<Seat> seats = seatRepository.findByNameIn(names);
        System.out.println(seats);

        // then
        assertThat(seats)
                .isNotEmpty()
                .allSatisfy((seat -> {
                    assertThat(names).contains(seat.getName());
                }));

    }



    private void insertSeatData() {
        this.seats = Arrays.asList(
                Seat.builder().name("A1").build(),
                Seat.builder().name("A2").build(),
                Seat.builder().name("A3").build(),
                Seat.builder().name("A4").build(),
                Seat.builder().name("A5").build(),

                Seat.builder().name("B1").build(),
                Seat.builder().name("B2").build(),
                Seat.builder().name("B3").build(),
                Seat.builder().name("B4").build(),
                Seat.builder().name("B5").build(),

                Seat.builder().name("C1").build(),
                Seat.builder().name("C2").build(),
                Seat.builder().name("C3").build(),
                Seat.builder().name("C4").build(),
                Seat.builder().name("C5").build(),

                Seat.builder().name("D1").build(),
                Seat.builder().name("D2").build(),
                Seat.builder().name("D3").build(),
                Seat.builder().name("D4").build(),
                Seat.builder().name("D5").build(),

                Seat.builder().name("E1").build(),
                Seat.builder().name("E2").build(),
                Seat.builder().name("E3").build(),
                Seat.builder().name("E4").build(),
                Seat.builder().name("E5").build()
        );
        seats.forEach(seatRepository::save);
    }
}