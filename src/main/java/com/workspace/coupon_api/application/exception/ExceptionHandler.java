package com.workspace.coupon_api.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidCouponCodeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCouponCode(InvalidCouponCodeException ex) {
        ErrorResponse body = new ErrorResponse("InvalidCouponCode", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidCouponDiscountException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCouponDiscount(InvalidCouponDiscountException ex) {
        ErrorResponse body = new ErrorResponse("InvalidCouponDiscount", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidExpirationDateException.class)
    public ResponseEntity<ErrorResponse> handleInvalidExpirationDate(InvalidExpirationDateException ex) {
        ErrorResponse body = new ErrorResponse("InvalidExpirationDate", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CouponAlreadyDeletedException.class)
    public ResponseEntity<ErrorResponse> handleCouponAlreadyDeleted(CouponAlreadyDeletedException ex) {
        ErrorResponse body = new ErrorResponse("CouponAlreadyDeleted", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleOtherExceptions(Exception ex) {
        ErrorResponse body = new ErrorResponse("InternalError", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
