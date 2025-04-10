package com.demo.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EmptyRequestFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final List<String> PUBLIC_ENDPOINTS = List.of(
            "/api/auth/sign-up",
            "/api/auth/sign-in",
            "/api/auth/forgot-password",
            "/api/auth/reset-password",
            "/api/auth"
    );

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return PUBLIC_ENDPOINTS.stream().anyMatch(path::equalsIgnoreCase);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {



        if (request.getMethod().equals("POST") && request.getRequestURI().endsWith("/api/auth/sign-up")) {
            if (request.getContentLength() <= 0) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("timestamp", new Date());
                errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
                errorResponse.put("error", "Bad Request");
                errorResponse.put("message", "Request body is missing or malformed");
                errorResponse.put("path", request.getRequestURI());

                response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
