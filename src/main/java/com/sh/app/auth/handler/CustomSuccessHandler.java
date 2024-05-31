package com.sh.app.auth.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
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
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException 
    {
        System.out.println("onAuthenticationSuccess진입");
        /**
         * 기본 redirect 페이지 지정
         */
        String targetUrl = "/";

        /**
         * 인증전 접근시도한 페이지가 session에 SPRING_SECURITY_SAVED_REQUEST 속성명으로 저장되어 있다.
         */
        SavedRequest savedRequest = (SavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        if(savedRequest != null)
        {
            targetUrl = savedRequest.getRedirectUrl(); //로그인 한 후 해당 페이지로 접근
        }
        else
        {
            System.out.println("savedRequest가 null입니다...");
        }
        System.out.println("targetUrl:"+targetUrl);
        log.debug("targetUrl = {}", targetUrl);

        //해당 targetUrl로 이동.
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
