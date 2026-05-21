package com.inventory.order.test.infrastructure.adapters.output.persistence;

import org.springframework.stereotype.Component;

import com.inventory.order.test.domain.model.Order;
import com.inventory.order.test.domain.ports.OrderRepositoryPort;
import com.inventory.order.test.infrastructure.entity.OrderEntity;

@Component
public class OrderPersistenceAdapter
        implements OrderRepositoryPort {

    private final OrderJpaRepository repository;

    public OrderPersistenceAdapter(
            OrderJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Order save(Order order) {

        OrderEntity entity = new OrderEntity();

        entity.setUserId(order.getUserId());
        entity.setStatus(order.getStatus());
        entity.setTotal(order.getTotal());

        OrderEntity saved = repository.save(entity);

        return order;
    }
}