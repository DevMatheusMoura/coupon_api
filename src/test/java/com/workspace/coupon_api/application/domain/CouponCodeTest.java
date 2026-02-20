package com.workspace.coupon_api.application.domain;

import com.workspace.coupon_api.application.exception.InvalidCouponCodeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CouponCodeTest {

    @Test
    void validCodeKeepsValue() {
        CouponCode code = new CouponCode("ABC123");
        assertEquals("ABC123", code.getCode());
    }

    @Test
    void validCodeWithSpecialCharactersIsCleaned() {
        CouponCode code = new CouponCode("A B-C@1#23");
        assertEquals("ABC123", code.getCode());
    }

    @Test
    void nullCodeThrows() {
        assertThrows(InvalidCouponCodeException.class, () -> new CouponCode(null));
    }

    @Test
    void blankCodeThrows() {
        assertThrows(InvalidCouponCodeException.class, () -> new CouponCode("     "));
    }

    @Test
    void wrongSizeThrows() {
        // after cleaning length != 6
        assertThrows(InvalidCouponCodeException.class, () -> new CouponCode("ABC12"));
    }
}

