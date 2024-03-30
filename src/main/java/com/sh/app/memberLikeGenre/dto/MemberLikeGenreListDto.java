package com.sh.app.memberLikeGenre.dto;

import com.sh.app.genre.dto.GenreInfoDto;
import com.sh.app.genre.entity.Genre;
import com.sh.app.member.dto.MemberInfoDto;
import com.sh.app.member.entity.Member;
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
