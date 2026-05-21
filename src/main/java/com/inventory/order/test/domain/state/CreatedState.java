package com.inventory.order.test.domain.state;

import com.inventory.order.test.domain.model.OrderStatus;

public class CreatedState implements OrderState {

	@Override
	public void next() {
		System.out.println("Order reserved");
	}

	@Override
	public OrderStatus getStatus() {
		return OrderStatus.CREATED;
	}

}
