package com.inventory.order.test.application.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Cacheable("products")
    public String getProducts() {

        return "Products from database";
    }

}
