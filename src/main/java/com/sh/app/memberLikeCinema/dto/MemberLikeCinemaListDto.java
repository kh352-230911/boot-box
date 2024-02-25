package com.sh.app.memberLikeCinema.dto;

import com.sh.app.cinema.entity.Cinema;
import lombok.Data;

@Data
public class MemberLikeCinemaListDto {
    private Long id;
    private Long memberId;
    private Cinema cinema;
}