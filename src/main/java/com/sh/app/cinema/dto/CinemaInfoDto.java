package com.sh.app.cinema.dto;

import com.sh.app.location.dto.LocationInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CinemaInfoDto {
    private String name;

    private String address;

    private LocationInfoDto location;
}
