package com.inventory.order.test.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class OrderService {

	@Transactional
    public void createOrder() {

        // reserve inventory
        // create order
        // publish event
        // rollback if fails
    }

}
