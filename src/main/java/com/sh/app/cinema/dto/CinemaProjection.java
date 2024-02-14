package com.sh.app.cinema.dto;

import java.time.LocalDate;
import java.time.LocalTime;


public interface CinemaProjection {
    LocalDate getSchDate();
    String getRegionCinema();
    String getTitle();
    String getName();
    LocalTime getTime();
    Integer getRemainingSeats();
}
