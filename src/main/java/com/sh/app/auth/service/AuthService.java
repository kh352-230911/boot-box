package com.sh.app.auth.service;

import com.sh.app.admin.entity.Admin;
import com.sh.app.admin.service.AdminService;
import com.sh.app.auth.vo.AdminDetails;
import com.sh.app.auth.vo.MemberDetails;
import com.sh.app.member.entity.Member;
import com.sh.app.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService implements UserDetailsService {
    @Autowired
    private MemberService memberService;
    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.findByUsername(username);
        log.debug("admin = {}", admin);
        if (admin == null) {
            Member member = memberService.findByMemberLoginId(username);
            log.debug("member = {}", member);
            if (member == null)
                throw new UsernameNotFoundException(username);
            return new MemberDetails(member);
        }
        return new AdminDetails(admin);
    }

    public void updateAuthentication(String username) {
        Member newMember = memberService.findByMemberLoginId(username);
        MemberDetails newMemberDetails = new MemberDetails(newMember);
        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
                newMemberDetails,
                newMemberDetails.getPassword(),
                newMemberDetails.getAuthorities()
        );
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(newAuthentication);
    }
}
