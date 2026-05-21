package com.inventory.order.test.infrastructure.adapters.output.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.inventory.order.test.domain.model.User;
import com.inventory.order.test.domain.ports.UserRepositoryPort;

@Service
public class CustomUserDetailsService
        implements UserDetailsService {

    private final UserRepositoryPort userRepositoryPort;

    public CustomUserDetailsService(
            UserRepositoryPort userRepositoryPort) {

        this.userRepositoryPort =
                userRepositoryPort;
    }

    @Override
    public UserDetails loadUserByUsername(
            String email)

            throws UsernameNotFoundException {

        User user =
                userRepositoryPort
                        .findByEmail(email)

                        .orElseThrow(() ->
                                new UsernameNotFoundException(
                                        "User not found"));

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(
                        user.getRole()
                                .name()
                                .replace("ROLE_", ""))
                .build();
    }
}