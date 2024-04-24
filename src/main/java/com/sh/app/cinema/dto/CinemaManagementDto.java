package com.sh.app.cinema.dto;

import com.sh.app.admin.dto.AdminListDto;
import com.sh.app.admin.entity.Admin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CinemaManagementDto {
    private String region_cinema;
    private String address;
}
