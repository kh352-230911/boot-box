package com.sh.app.cinema.dto;

import com.sh.app.location.entity.Location;
import com.sh.app.memberLikeCinema.dto.MemberLikeCinemaDto;
import com.sh.app.theater.entity.Theater;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CinemaDto {
    private Long id;
    private String region_cinema;
    private int theater_number;
    private String address;
    private Double location_lo; // 지도 경도
    private Double location_la; // 지도 위도
    private String phone;

    private Location location;
    private String location_name;

    private List<Theater> theaters = new ArrayList<>();

    private List<MemberLikeCinemaDto> memberLikeCinemas = new ArrayList<>();
}