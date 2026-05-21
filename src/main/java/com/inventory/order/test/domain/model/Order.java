package com.inventory.order.test.domain.model;

import java.math.BigDecimal;
import java.util.List;

public class Order {

    private Long id;

    private Long userId;

    private List<OrderItem> items;

    private OrderStatus status;

    private BigDecimal total;

    public Order() {
    }

    public Order(
            Long id,
            Long userId,
            List<OrderItem> items,
            OrderStatus status) {

        this.id = id;
        this.userId = userId;
        this.items = items;
        this.status = status;

        calculateTotal();
    }

    private void calculateTotal() {

        this.total = items.stream()
                .map(OrderItem::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void markAsReserved() {

        if (status != OrderStatus.CREATED) {
            throw new RuntimeException("Invalid state transition");
        }

        status = OrderStatus.RESERVED;
    }

    public void markAsPaid() {

        if (status != OrderStatus.RESERVED) {
            throw new RuntimeException("Invalid state transition");
        }

        status = OrderStatus.PAID;
    }

    public void cancel() {

        if (status == OrderStatus.SHIPPED ||
                status == OrderStatus.DELIVERED) {

            throw new RuntimeException("Cannot cancel delivered order");
        }

        status = OrderStatus.CANCELLED;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public BigDecimal getTotal() {
        return total;
    }
}