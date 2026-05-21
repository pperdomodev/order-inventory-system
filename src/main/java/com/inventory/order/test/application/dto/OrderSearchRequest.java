package com.inventory.order.test.application.dto;

import java.math.BigDecimal;

import com.inventory.order.test.domain.model.OrderStatus;

public class OrderSearchRequest {

    private OrderStatus status;

    private Long userId;

    private BigDecimal minTotal;

    private BigDecimal maxTotal;

    public OrderSearchRequest() {
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Long getUserId() {
        return userId;
    }

    public BigDecimal getMinTotal() {
        return minTotal;
    }

    public BigDecimal getMaxTotal() {
        return maxTotal;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setMinTotal(BigDecimal minTotal) {
        this.minTotal = minTotal;
    }

    public void setMaxTotal(BigDecimal maxTotal) {
        this.maxTotal = maxTotal;
    }
}