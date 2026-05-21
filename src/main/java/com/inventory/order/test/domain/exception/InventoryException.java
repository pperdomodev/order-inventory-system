package com.inventory.order.test.domain.exception;

public class InventoryException
        extends BusinessException {

	    private static final long serialVersionUID = 1L;

	public InventoryException(String message) {

        super(message);
    }
}