package com.sh.app.config;

import com.sh.app.auth.handler.CustomSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    OAuth2UserService oAuth2UserService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/favicon.ico","/css/**", "/images/**", "/js/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((authorizeRequest -> {
            authorizeRequest
                    .requestMatchers("/", "/index.html").permitAll()
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/cinema/**").permitAll()
                    .requestMatchers("/movie/**").permitAll()
                    .requestMatchers("/reservation/**").permitAll()
                    .requestMatchers("/reservation1/**").permitAll()
                    .requestMatchers("/admin/adminAuth.do").permitAll()
                    .requestMatchers("/member/memberDetail.do").authenticated()
                    .requestMatchers("/notice/**").permitAll()
                    .requestMatchers("/ask/**").authenticated() // 인증된 사용자만 접근가능
                    .requestMatchers("/member/**").permitAll()
                    .requestMatchers("/member/createMember.do", "/member/checkIdDuplicate.do").anonymous()
//                    .requestMatchers("/board/**").authenticated()
//                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/admin/**").hasAnyRole("ADMIN", "MANAGER")
                    .requestMatchers("/notice/createNotice.do").hasAnyRole("ADMIN", "MANAGER")
                    .requestMatchers("/notice/deleteNotice.do").hasAnyRole("ADMIN", "MANAGER")
                    .requestMatchers("/ask/askList.do").hasAnyRole("ADMIN", "MANAGER")
                    .requestMatchers("/ask/askDetail.do").hasAnyRole("ADMIN", "MANAGER")
                    .anyRequest().authenticated();
        }));

        http.formLogin((formLoginConfigurer -> {
            formLoginConfigurer
                    .loginPage("/auth/login.do") // 로그인 폼페이지(GET) (작업필요)
                    .loginProcessingUrl("/auth/login.do") // 로그인 처리 (POST)
                    .successHandler(new CustomSuccessHandler()) //성공시 수행할 핸들러
                    .permitAll();
        }));
        http.logout(logoutConfigurer -> {
            logoutConfigurer
                    .logoutUrl("/auth/logout.do") // 로그아웃처리(POST)
                    .logoutSuccessUrl("/");
        });
        http.oauth2Login(httpSecurityOAuth2LoginConfigurer -> {
            httpSecurityOAuth2LoginConfigurer
                    .loginPage("/auth/login.do")
                    .userInfoEndpoint(userInfoEndpointConfig -> {
                        userInfoEndpointConfig.userService(oAuth2UserService);
                    });
        });
        return http.build();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 랜덤솔트 값을 사용
    }
}
