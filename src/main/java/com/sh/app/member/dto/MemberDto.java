package com.sh.app.member.dto;

import com.sh.app.authority.entity.Authority;
import com.sh.app.review.entity.Review;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MemberDto {
    private Long id;
    private String memberLoginId;
    private String memberEmail;
    private String memberName;
    private String memberPhone;
    private String birthyear;
    private List<Review> reviews = new ArrayList<>();
    private List<Authority> authorities = new ArrayList<>();
}
