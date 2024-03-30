package com.sh.app.auth.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import java.io.IOException;

@Slf4j
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {

        // 로그인 후 처리 작업
        // 1.알림테이블에서 미확인 알림을 조회
        // 2. ..

        String targetUrl = determineTargetUrl(request);

        log.debug("targetUrl = {}", targetUrl);

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request) {
        SavedRequest savedRequest = requestCache.getRequest(request, null);

        if (savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
            System.out.println("=====================이전페이지가 저장되어있어요!=====================");
            // 로그인 페이지 또는 로그인 페이지로 리다이렉트하는 URL인지 확인
            if (!isLoginUrl(redirectUrl)) {
                return redirectUrl;
            }
        } else {
            System.out.println("=====================이전페이지가 저장되어있지 않아요!=====================");
        }

        // 기본 페이지로 리다이렉션
        return "/";
    }

    protected boolean isLoginUrl(String url) {
        return url.contains("/login");
    }
}
