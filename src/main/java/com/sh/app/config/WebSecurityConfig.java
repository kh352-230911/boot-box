package com.sh.app.config;

import com.sh.app.auth.handler.CustomFailureHandler;
import com.sh.app.auth.handler.CustomSuccessHandler;
import com.sh.app.oauth.service.OAuth2UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    OAuth2UserService oAuth2UserService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/css/**", "/images/**", "/js/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((authorizeRequest -> {
            authorizeRequest
                    .requestMatchers("/", "/index.html").permitAll()
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/cinema/**").permitAll()
                    .requestMatchers("/movie/**").permitAll()
                    .requestMatchers("/member/createMember.do", "/member/checkIdDuplicate.do").anonymous()
//                    .requestMatchers("/board/**").authenticated()
//                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated();
        }));

        http.formLogin((formLoginConfigurer -> {
            formLoginConfigurer
                    .loginPage("/auth/login.do") // 로그인 폼페이지(GET) (작업필요)
                    .loginPage("/auth/adminLogin.do")
                    .loginProcessingUrl("/auth/login.do") // 로그인 처리 (POST)
                    .loginProcessingUrl("/auth/adminLogin.do")
                    .successHandler(new CustomSuccessHandler())
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
