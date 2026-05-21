package com.inventory.order.test.infrastructure.adapters.output.persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.inventory.order.test.domain.model.User;
import com.inventory.order.test.domain.ports.UserRepositoryPort;
import com.inventory.order.test.infrastructure.entity.UserEntity;

@Component
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final UserJpaRepository repository;

    public UserPersistenceAdapter(UserJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {

        UserEntity entity = new UserEntity(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRole());

        UserEntity saved = repository.save(entity);

        return new User(
                saved.getId(),
                saved.getUsername(),
                saved.getEmail(),
                saved.getPassword(),
                saved.getRole());
    }

    @Override
    public Optional<User> findByEmail(String email) {

        return repository.findByEmail(email)
                .map(entity -> new User(
                        entity.getId(),
                        entity.getUsername(),
                        entity.getEmail(),
                        entity.getPassword(),
                        entity.getRole()));
    }
}
