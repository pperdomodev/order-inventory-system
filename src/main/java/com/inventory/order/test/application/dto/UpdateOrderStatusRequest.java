package com.inventory.order.test.application.dto;

import com.inventory.order.test.domain.model.OrderStatus;

public class UpdateOrderStatusRequest {

    private OrderStatus status;

    private String comment;

    private String changedBy;

    public UpdateOrderStatusRequest() {
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getComment() {
        return comment;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }
}