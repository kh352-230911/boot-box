package com.sh.app.cinema.dto;

import com.sh.app.location.entity.Location;
import com.sh.app.theater.entity.Theater;
<<<<<<< HEAD
import jakarta.persistence.*;
import lombok.Builder;
=======
>>>>>>> f8fce2ba6c70d83f95237d6b72a985fba70a1001
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CinemaDTO {
    private Long id;
    private String region_cinema;
    private int theater_number;
    private String address;
<<<<<<< HEAD
    private int location_lo; // 지도 경도
    private int location_la; // 지도 위도
    private String phone;

    private Location location;
=======
    private Double location_lo; // 지도 경도
    private Double location_la; // 지도 위도
    private String phone;

    private Location location;
    private String location_name;
>>>>>>> f8fce2ba6c70d83f95237d6b72a985fba70a1001

    private List<Theater> theaters = new ArrayList<>();
}
