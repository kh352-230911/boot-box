package com.sh.app.auth.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
public class CustomFailureHandler implements AuthenticationFailureHandler {
 
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        /**
         * 기본 redirect 페이지 지정
         */
        System.out.println("onAuthenticationFailure 진입");

        String errorMessage="";
        if (exception instanceof BadCredentialsException)
        {
            errorMessage = "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해 주세요.";
        }
        else if (exception instanceof UsernameNotFoundException)
        {
            errorMessage = "계정이 존재하지 않습니다. 회원가입 진행 후 로그인 해주세요.";
        }
        else if (exception instanceof AuthenticationCredentialsNotFoundException)
        {
            errorMessage = "인증 요청이 거부되었습니다. 관리자에게 문의하세요.";
        }
        else
        {
            errorMessage = "알 수 없는 이유로 로그인에 실패하였습니다.(관리자에게 문의)";
        }
        System.out.println("로그인 실패, 이유:"+errorMessage);
        //setDefaultFailureUrl("/auth/login?error=true&exception="+errorMessage);
        //super.onAuthenticationFailure(request, response, exception);
        // 에러 메시지를 로그인 페이지로 전달하여 사용자에게 표시
        String redirectUrl = "/auth/login.do?error=true&exception=" + URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        redirectStrategy.sendRedirect(request, response, redirectUrl);

    }

}