package com.sh.app.admin.dto;

import com.sh.app.authority.entity.Authority;
import com.sh.app.cinema.dto.CinemaManagementDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminListDto {
    private Long id;

    private String username;

    private List<Authority> authorities;

    private Long cinemaId;

    private CinemaManagementDto cinemaManagementDto;
}
