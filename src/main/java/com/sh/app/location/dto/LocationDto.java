package com.sh.app.location.dto;

import com.sh.app.cinema.dto.CinemaDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LocationDto {
    private Long id;
    private String location_name;
    private List<CinemaDto> cinemas = new ArrayList<>();
}
