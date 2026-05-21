package com.inventory.order.test.application.dto;

import java.util.List;

public class CreateOrderRequest {

    private Long userId;

    private List<OrderItemRequest> items;

    public CreateOrderRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }
}