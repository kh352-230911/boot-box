package com.sh.app.cinema.dto;

import lombok.Data;

@Data
public class CinemaDto {
    private Long id;
    private String region_cinema;
    private int theater_number;
    private String address;
    private int location_lo;
    private int location_la;
    private String phone;
    private String location_name; // Location#location_name
}
