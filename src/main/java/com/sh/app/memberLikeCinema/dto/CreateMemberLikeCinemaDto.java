package com.sh.app.memberLikeCinema.dto;

import lombok.Data;

@Data
public class CreateMemberLikeCinemaDto {
    private Long id;
    private Long memberId;
    private Long cinemaId;
    private int likeCinemaCount;


}
