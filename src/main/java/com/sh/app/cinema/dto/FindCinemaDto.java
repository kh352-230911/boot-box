package com.sh.app.cinema.dto;

import lombok.Data;

//지점의 아이디와, 지점이름
@Data
public class FindCinemaDto {
    private Long id;
    private String regionCinema;
}
