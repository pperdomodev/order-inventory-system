package com.inventory.order.test.infrastructure.config;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuditFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)

            throws ServletException, IOException {

        System.out.println(
                "AUDIT -> "
                        + request.getMethod()
                        + " "
                        + request.getRequestURI());

        filterChain.doFilter(request, response);
    }
}