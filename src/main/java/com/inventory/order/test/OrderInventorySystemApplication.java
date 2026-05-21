package com.inventory.order.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class OrderInventorySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderInventorySystemApplication.class, args);
	}

}
