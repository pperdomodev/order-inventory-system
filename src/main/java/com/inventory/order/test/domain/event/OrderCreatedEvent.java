package com.inventory.order.test.domain.event;

public class OrderCreatedEvent {

	private Long orderId;

    public OrderCreatedEvent(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

}
