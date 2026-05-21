package com.inventory.order.test.shared.response;

import java.time.LocalDateTime;

public class ErrorResponse {

    private boolean success;

    private String message;

    private String code;

    private LocalDateTime timestamp;

    public ErrorResponse(
            String message,
            String code) {

        this.success = false;
        this.message = message;
        this.code = code;
        this.timestamp = LocalDateTime.now();
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}