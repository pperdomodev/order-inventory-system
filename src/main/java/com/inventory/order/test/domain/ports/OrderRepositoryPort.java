package com.inventory.order.test.domain.ports;

import com.inventory.order.test.domain.model.Order;

public interface OrderRepositoryPort {

    Order save(Order order);
}