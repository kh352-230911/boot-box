package com.sh.app.member.service;


import com.sh.app.authority.entity.Authority;
import com.sh.app.authority.entity.RoleAuth;
import com.sh.app.authority.service.AuthorityService;
import com.sh.app.member.entity.Member;
import com.sh.app.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional //기존 mvc패턴 처럼 rollback 처리. class 단에서 선언하면 하위 모든 메소드에도 모두 어노테이션 처리됨
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AuthorityService authorityService;

    public Member findByMemberLoginId(String username) {
        return memberRepository.findByMemberLoginId(username);
    }

    public Member createMember(Member member) {
        memberRepository.save(member);
        Authority authority = Authority.builder()
                .memberId(member.getId())
                .name(RoleAuth.ROLE_USER)
                .build();
        authorityService.createAuthority(authority);
        return member;
    }

}
