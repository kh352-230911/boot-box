package com.sh.app.member.dto;

import com.sh.app.ask.dto.AskInfoDto;
import com.sh.app.authority.entity.Authority;
import com.sh.app.memberLikeGenre.entity.MemberLikeGenre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberAskDto {
    private Long id;

    private String memberLoginId;

    private String memberName;

    private List<AskInfoDto> asks;
}
