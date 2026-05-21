package com.inventory.order.test.domain.model;

public class Inventory {

	private Long id;
    private Long productId;
    private Integer availableQuantity;
    private Integer reservedQuantity;

    public Inventory(Long id, Long productId, Integer availableQuantity, Integer reservedQuantity) {
		this.id = id;
		this.productId = productId;
		this.availableQuantity = availableQuantity;
		this.reservedQuantity = reservedQuantity;
	}

	public void reserve(Integer quantity) {

        if (availableQuantity < quantity) {
            throw new RuntimeException("Insufficient stock");
        }

        availableQuantity -= quantity;
        reservedQuantity += quantity;
    }

    public void release(Integer quantity) {

        reservedQuantity -= quantity;
        availableQuantity += quantity;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public Integer getReservedQuantity() {
		return reservedQuantity;
	}

	public void setReservedQuantity(Integer reservedQuantity) {
		this.reservedQuantity = reservedQuantity;
	}

}
