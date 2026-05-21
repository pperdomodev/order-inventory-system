package com.inventory.order.test.application.dto;

import java.math.BigDecimal;

public class OrderItemRequest {

    private Long productId;

    private Integer quantity;

    private BigDecimal price;

    public OrderItemRequest() {
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

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}