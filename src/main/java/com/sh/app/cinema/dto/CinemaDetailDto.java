package com.sh.app.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CinemaDetailDto {
    private LocalDate schDate;
    private String region_cinema;
    private String title;
    private String theaterName;
    private LocalTime startTime;
    private Integer remainingSeats;
}
