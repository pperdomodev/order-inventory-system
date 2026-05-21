package com.inventory.order.test.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.inventory.order.test.infrastructure.adapters.output.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    private final UserDetailsService userDetailsService;

    private final AuditFilter auditFilter;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthFilter,
            UserDetailsService userDetailsService,
            AuditFilter auditFilter) {

        this.jwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;
        this.auditFilter = auditFilter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {

        http

            // disable csrf
            .csrf(csrf -> csrf.disable())

            // stateless JWT
            .sessionManagement(session ->
                session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS)
            )

            // security headers
            .headers(headers -> headers

                .frameOptions(frame ->
                        frame.deny())

                .contentSecurityPolicy(csp ->
                        csp.policyDirectives(
                                "default-src 'self'"))

                .xssProtection(xss ->
                        xss.disable())
            )

            // endpoint authorization
            .authorizeHttpRequests(auth -> auth

                .requestMatchers(
                        "/auth/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**"
                ).permitAll()

                .requestMatchers(
                        "/api/v1/products/**"
                ).hasAnyRole("ADMIN", "USER")

                .requestMatchers(
                        "/api/v1/inventory/**"
                ).hasRole("ADMIN")

                .requestMatchers(
                        "/api/v1/orders/**"
                ).hasAnyRole("ADMIN", "USER")

                .anyRequest()
                .authenticated()
            )

            // auth provider
            .authenticationProvider(
                    authenticationProvider())

            // audit filter
            .addFilterBefore(
                    auditFilter,
                    UsernamePasswordAuthenticationFilter.class
            )

            // jwt filter
            .addFilterBefore(
                    jwtAuthFilter,
                    UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authProvider =
                new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(
                userDetailsService);

        authProvider.setPasswordEncoder(
                passwordEncoder());

        return authProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}