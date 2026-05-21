package com.inventory.order.test.domain.model;

import java.math.BigDecimal;

public class OrderItem {

    private Long productId;

    private Integer quantity;

    private BigDecimal price;

    public OrderItem() {
    }

    public OrderItem(
            Long productId,
            Integer quantity,
            BigDecimal price) {

        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public BigDecimal subtotal() {

        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }
}