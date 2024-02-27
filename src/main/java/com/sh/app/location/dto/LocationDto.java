package com.sh.app.location.dto;

import com.sh.app.cinema.dto.CinemaDto;
import com.sh.app.cinema.dto.CinemaTitleDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LocationDto {
    private Long id;
    private String location_name;
    private List<CinemaTitleDto> cinemas = new ArrayList<>();
}
