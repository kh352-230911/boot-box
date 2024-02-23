package com.sh.app.member.service;


import com.sh.app.authority.entity.Authority;
import com.sh.app.authority.entity.RoleAuth;
import com.sh.app.authority.service.AuthorityService;
import com.sh.app.member.entity.Member;
import com.sh.app.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional //기존 mvc패턴 처럼 rollback 처리. class 단에서 선언하면 하위 모든 메소드에도 모두 어노테이션 처리됨
public class MemberService {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

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

    public Member updateMember(Member member) {
        return memberRepository.save(member);
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    public void logoutAndInvalidateSession() {
        // SecurityContextLogoutHandler 인스턴스 생성
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

        // 로그아웃 처리: 현재 세션 무효화 및 SecurityContext 클리어
        logoutHandler.logout(request, response, null);
    }

    public List<Member> findAll() {
        System.out.println("회원조회 service");
        return memberRepository.findAll();
    }

    public Member findByReservation(Long id) {
        return memberRepository.findByReservation(id);
    }

    public Optional<Member> findById(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
