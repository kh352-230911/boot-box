package com.sh.app.oauth.service;

import com.sh.app.auth.service.AuthService;
import com.sh.app.auth.vo.MemberDetails;
import com.sh.app.genre.entity.Genre;
import com.sh.app.member.dto.MemberCreateDto;
import com.sh.app.member.entity.Member;
import com.sh.app.member.service.MemberService;
import com.sh.app.oauth.utils.OAuth2UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {
    @Autowired
    AuthService authService;
    @Autowired
    MemberService memberService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        MemberDetails memberDetails = null;

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        OAuth2AccessToken accessToken = userRequest.getAccessToken();
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        log.debug("clientRegistration = {}", clientRegistration);
        log.debug("accessToken = {}", accessToken);
        log.debug("oAuth2User = {}", oAuth2User);
        log.debug("attributes = {}", attributes);

        String provider = clientRegistration.getRegistrationId();

        String username = OAuth2UserUtils.getUsername(oAuth2User, provider);

        try {
            memberDetails = (MemberDetails) authService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            Member member = OAuth2UserUtils.of(oAuth2User, provider);

            // 여기서 실제 사용자의 선호 장르를 가져와야 함
            String userPreferredGenre = getUserPreferredGenreFromOAuth2UserAttributes(attributes);

            Genre genre = new Genre();
            genre.setGenreName(userPreferredGenre);
            member.addGenre(genre);

            memberService.createMember(member, genre);
            memberDetails = (MemberDetails) authService.loadUserByUsername(username);
        }
        return memberDetails;
    }

    private String getUserPreferredGenreFromOAuth2UserAttributes(Map<String, Object> attributes) {
        String preferredGenre = null;

        // 사용자가 선택한 장르를 가져오는 로직
        // 여기서는 선택된 라디오 버튼의 값을 가져와 사용
        Object selectedGenreObject = attributes.get("selectedGenre");

        if (selectedGenreObject != null) {
            preferredGenre = selectedGenreObject.toString();
        }

        return preferredGenre;
    }

}
