package com.inventory.order.test.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.order.test.application.dto.CreateOrderRequest;
import com.inventory.order.test.application.dto.OrderItemRequest;
import com.inventory.order.test.application.dto.UpdateOrderStatusRequest;
import com.inventory.order.test.domain.event.OrderCreatedEvent;
import com.inventory.order.test.domain.model.Inventory;
import com.inventory.order.test.domain.model.Order;
import com.inventory.order.test.domain.model.OrderItem;
import com.inventory.order.test.domain.model.OrderStatus;
import com.inventory.order.test.domain.ports.InventoryRepositoryPort;
import com.inventory.order.test.domain.ports.OrderRepositoryPort;
import com.inventory.order.test.infrastructure.adapters.output.messaging.EventPublisher;
import com.inventory.order.test.infrastructure.adapters.output.persistence.OrderJpaRepository;
import com.inventory.order.test.infrastructure.adapters.output.persistence.OrderStatusHistoryJpaRepository;
import com.inventory.order.test.infrastructure.entity.OrderEntity;
import com.inventory.order.test.infrastructure.entity.OrderStatusHistoryEntity;

@Service
public class OrderService {

    private final InventoryRepositoryPort inventoryRepositoryPort;

    private final OrderRepositoryPort orderRepositoryPort;
    
    private final OrderJpaRepository orderJpaRepository;

    private final OrderStatusHistoryJpaRepository historyRepository;

    private final EventPublisher eventPublisher;
    
    public OrderService(
            InventoryRepositoryPort inventoryRepositoryPort,
            OrderRepositoryPort orderRepositoryPort,
            EventPublisher eventPublisher,
            OrderJpaRepository orderJpaRepository,
            OrderStatusHistoryJpaRepository historyRepository) {

        this.inventoryRepositoryPort =
                inventoryRepositoryPort;

        this.orderRepositoryPort =
                orderRepositoryPort;

        this.eventPublisher =
                eventPublisher;

        this.orderJpaRepository =
                orderJpaRepository;

        this.historyRepository =
                historyRepository;
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
    
    @Transactional
    public void updateStatus(
            Long orderId,
            UpdateOrderStatusRequest request) {

        OrderEntity order =
                orderJpaRepository.findById(orderId)

                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Order not found"));

        OrderStatus previousStatus =
                order.getStatus();

        validateTransition(
                previousStatus,
                request.getStatus());

        order.setStatus(request.getStatus());

        orderJpaRepository.save(order);

        OrderStatusHistoryEntity history =
                new OrderStatusHistoryEntity();

        history.setOrderId(orderId);

        history.setPreviousStatus(
                previousStatus.name());

        history.setNewStatus(
                request.getStatus().name());

        history.setChangedBy(
                request.getChangedBy());

        history.setComment(
                request.getComment());

        historyRepository.save(history);

        eventPublisher.publish(
                new OrderCreatedEvent(orderId));
    }
    
    public List<OrderStatusHistoryEntity>
    history(Long orderId) {

        return historyRepository
                .findByOrderId(orderId);
    }
    
    private void validateTransition(
            OrderStatus current,
            OrderStatus next) {

        if (current == OrderStatus.CREATED
                && next == OrderStatus.RESERVED) {
            return;
        }

        if (current == OrderStatus.RESERVED
                && next == OrderStatus.PAID) {
            return;
        }

        if (current == OrderStatus.PAID
                && next == OrderStatus.PROCESSING) {
            return;
        }

        if (current == OrderStatus.PROCESSING
                && next == OrderStatus.SHIPPED) {
            return;
        }

        if (current == OrderStatus.SHIPPED
                && next == OrderStatus.DELIVERED) {
            return;
        }

        if (next == OrderStatus.CANCELLED) {
            return;
        }

        throw new RuntimeException(
                "Invalid status transition");
    }
}