package com.dc.pharmacy.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        String errorMessage = "Unauthorized";

        if (authException instanceof UsernameNotFoundException) {
            errorMessage = "Invalid username";
        } else if (authException instanceof BadCredentialsException) {
            errorMessage = "Invalid password";
        }

        response.getWriter().write("{\"error\": \"" + errorMessage + "\"}");
    }
}
