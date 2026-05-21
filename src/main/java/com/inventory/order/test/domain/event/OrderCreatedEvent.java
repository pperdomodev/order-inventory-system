package com.inventory.order.test.domain.event;

import java.io.Serializable;

public class OrderCreatedEvent implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long orderId;

    public OrderCreatedEvent(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

}
