package com.inventory.order.test.infrastructure.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.inventory.order.test.domain.model.OrderStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private BigDecimal total;

    @Version
    private Long version;

    private LocalDateTime createdAt;

    public OrderEntity() {
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Long getVersion() {
        return version;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
} 