package com.inventory.order.test.infrastructure.adapters.output.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.order.test.infrastructure.entity.OrderStatusHistoryEntity;

public interface OrderStatusHistoryJpaRepository
        extends JpaRepository<OrderStatusHistoryEntity, Long> {

    List<OrderStatusHistoryEntity>
    findByOrderId(Long orderId);
}