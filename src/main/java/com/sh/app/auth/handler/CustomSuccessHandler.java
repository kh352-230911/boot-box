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


    //0219 jin - 로그인 후 ,로그인 이전 페이지로 바로 이동하는 기능으로 수정하기.
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
//        throws IOException {
//            String targetUrl = "/";
//
//            SavedRequest savedRequest = (SavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
//            if(savedRequest != null) {
//
//                targetUrl = savedRequest.getRedirectUrl();
//            }
//
//            log.debug("----------------targetUrl = {}", targetUrl);
//
//            redirectStrategy.sendRedirect(request, response, targetUrl);
//    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {

        String targetUrl = "/";

        SavedRequest savedRequest =
                (SavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");

        //이전페이지가 저장 되어있는 경우
        if(savedRequest != null) {

            targetUrl = savedRequest.getRedirectUrl();
            redirectStrategy.sendRedirect(request,response,targetUrl);
        }
        else {
            System.out.println("savedRequest == null ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ");
            redirectStrategy.sendRedirect(request, response, targetUrl);
        }

//        log.debug("----------------targetUrl = {}", targetUrl);


    }


}
