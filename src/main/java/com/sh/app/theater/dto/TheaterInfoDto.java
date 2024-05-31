package com.sh.app.theater.dto;

import com.sh.app.cinema.dto.CinemaInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheaterInfoDto {
    private String name;

    private CinemaInfoDto cinema;
}
