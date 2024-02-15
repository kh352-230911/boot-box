package com.sh.app.cinema.dto;

import com.sh.app.location.entity.Location;
import com.sh.app.theater.entity.Theater;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CinemaDTO {
    private Long id;
    private String region_cinema;
    private int theater_number;
    private String address;
    private int location_lo; // 지도 경도
    private int location_la; // 지도 위도
    private String phone;

    private Location location;

    private List<Theater> theaters = new ArrayList<>();
}
