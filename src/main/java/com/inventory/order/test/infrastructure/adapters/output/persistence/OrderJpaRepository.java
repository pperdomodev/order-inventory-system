package com.inventory.order.test.infrastructure.adapters.output.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.order.test.infrastructure.entity.OrderEntity;

public interface OrderJpaRepository
        extends JpaRepository<OrderEntity, Long> {
}