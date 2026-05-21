package com.inventory.order.test.domain.exception;

public class BusinessException
        extends RuntimeException {

	    private static final long serialVersionUID = 1L;

	public BusinessException(String message) {

        super(message);
    }
}