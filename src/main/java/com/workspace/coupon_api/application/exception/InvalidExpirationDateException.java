package com.workspace.coupon_api.application.exception;

public class InvalidExpirationDateException extends RuntimeException {
    public InvalidExpirationDateException(String message) {
        super(message);
    }

    public InvalidExpirationDateException(String message, Throwable cause) {
        super(message, cause);
    }
}

