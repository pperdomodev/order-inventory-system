package com.inventory.order.test.infrastructure.adapters.input.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

	@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/products")
    public String createProduct() {

        return "Product created";
    }

}
