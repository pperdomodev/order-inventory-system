package com.inventory.order.test.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.order.test.application.dto.CreateOrderRequest;
import com.inventory.order.test.application.dto.OrderItemRequest;
import com.inventory.order.test.domain.event.OrderCreatedEvent;
import com.inventory.order.test.domain.model.Inventory;
import com.inventory.order.test.domain.model.Order;
import com.inventory.order.test.domain.model.OrderItem;
import com.inventory.order.test.domain.model.OrderStatus;
import com.inventory.order.test.domain.ports.InventoryRepositoryPort;
import com.inventory.order.test.domain.ports.OrderRepositoryPort;
import com.inventory.order.test.infrastructure.adapters.output.messaging.EventPublisher;

@Service
public class OrderService {

    private final InventoryRepositoryPort inventoryRepositoryPort;

    private final OrderRepositoryPort orderRepositoryPort;

    private final EventPublisher eventPublisher;

    public OrderService(
            InventoryRepositoryPort inventoryRepositoryPort,
            OrderRepositoryPort orderRepositoryPort,
            EventPublisher eventPublisher) {

        this.inventoryRepositoryPort = inventoryRepositoryPort;
        this.orderRepositoryPort = orderRepositoryPort;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public void createOrder(CreateOrderRequest request) {

        List<OrderItem> items = request.getItems()
                .stream()
                .map(this::mapItem)
                .toList();

        for (OrderItem item : items) {

            Inventory inventory =
                    inventoryRepositoryPort
                            .findByProductId(item.getProductId())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Inventory not found"));

            inventory.reserve(item.getQuantity());

            inventoryRepositoryPort.save(inventory);
        }

        Order order = new Order(
                null,
                request.getUserId(),
                items,
                OrderStatus.CREATED);

        Order savedOrder =
                orderRepositoryPort.save(order);

        eventPublisher.publish(
        	    new OrderCreatedEvent(savedOrder.getId()));
    }

    private OrderItem mapItem(OrderItemRequest item) {

        return new OrderItem(
                item.getProductId(),
                item.getQuantity(),
                item.getPrice());
    }
}