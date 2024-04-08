package com.sh.app.oauth.utils;

import com.sh.app.member.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class OAuth2UserUtils {
    public static Member of(OAuth2User oAuth2User, String provider) {
        return switch (provider) {
            case "google" -> google(oAuth2User);
            case "kakao" -> kakao(oAuth2User);
            default -> throw new AssertionError(provider);
        };
    }

    private static Member kakao(OAuth2User oAuth2User) {
        String username = oAuth2User.getAttribute("id") + "@kakao";
        Map<String, Object> properties = oAuth2User.getAttribute("properties");
        String name = (String) properties.get("nickname");
        return Member.builder()
                .memberLoginId(username)
                .memberPwd("1234")
                .memberName(name)
                .build();
    }

    private static Member google(OAuth2User oAuth2User) {
        String username = oAuth2User.getAttribute("sub") + "@google";
        String name = oAuth2User.getAttribute("name");
        String email = oAuth2User.getAttribute("email");
        return Member.builder()
                .memberLoginId(username)
                .memberPwd("1234")
                .memberName(name)
                .memberEmail(email)
                .birthyear("정보 없음")
                .build();
    }

    public static String getUsername(OAuth2User oAuth2User, String provider) {
        return switch (provider) {
            case "google" -> oAuth2User.getAttribute("sub") + "@google";
            case "kakao" -> oAuth2User.getAttribute("id") + "@kakao";
            default -> throw new AssertionError(provider);
        };
    }
}
