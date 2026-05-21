package com.inventory.order.test.domain.state;

import com.inventory.order.test.domain.model.OrderStatus;

public interface OrderState {

	void next();

    OrderStatus getStatus();

}
