package com.sh.app.auth.vo;

import com.sh.app.admin.entity.Admin;
import com.sh.app.member.entity.Member;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

@Data
public class MemberDetails implements UserDetails, OAuth2User, Serializable {
    final Member member;

    private Map<String, Object> attributes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.member.getAuthorities().stream()
                .map((authority -> authority.getName().name()))
                .map((roleAuth) -> new SimpleGrantedAuthority(roleAuth))
                .toList();
    }

    @Override
    public String getPassword() {
        return this.member.getMemberPwd();
    }

    @Override
    public String getUsername() {
        return this.member.getMemberLoginId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return this.member.getMemberLoginId();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }
}
