package com.workspace.coupon_api.application.exception;

public class InvalidCouponDiscountException extends RuntimeException {
    public InvalidCouponDiscountException(String message) {
        super(message);
    }

    public InvalidCouponDiscountException(String message, Throwable cause) {
        super(message, cause);
    }
}

