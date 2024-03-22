package com.sh.app.actor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActorDetailDto {
    private Long id;

    private Long actorId;

    private String actorNm;
}
