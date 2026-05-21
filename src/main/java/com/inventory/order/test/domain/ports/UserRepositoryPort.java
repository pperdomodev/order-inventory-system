package com.inventory.order.test.domain.ports;

import java.util.Optional;

import com.inventory.order.test.domain.model.User;


public interface UserRepositoryPort {

    User save(User user);

    Optional<User> findByEmail(String email);
}
