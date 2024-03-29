package com.geospatial.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = "";

        boolean isClient = authentication.getAuthorities().stream().anyMatch(auth -> "ROLE_CLIENT".equals(auth.getAuthority()));

        if(isClient == true) {
            targetUrl = "geo-home";
        }
        if(response.isCommitted()){
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);

        
    }

    
}