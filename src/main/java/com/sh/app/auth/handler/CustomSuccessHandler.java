package com.sh.app.auth.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;

import java.io.IOException;

@Slf4j
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException {
            String targetUrl = "/";

            SavedRequest savedRequest = (SavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
            if(savedRequest != null) {
                targetUrl = savedRequest.getRedirectUrl();
            }

            log.debug("targetUrl = {}", targetUrl);

            redirectStrategy.sendRedirect(request, response, targetUrl);
    }
}
