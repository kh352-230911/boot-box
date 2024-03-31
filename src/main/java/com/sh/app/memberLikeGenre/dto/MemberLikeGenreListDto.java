package com.sh.app.memberLikeGenre.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberLikeGenreListDto {

    private Long id;
    private String memberName;
    private String genreName;
}
