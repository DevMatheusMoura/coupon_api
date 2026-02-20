package com.workspace.coupon_api.application.service;

import com.workspace.coupon_api.application.api.CouponRequest;
import com.workspace.coupon_api.application.api.CouponResponse;
import com.workspace.coupon_api.application.domain.CouponStatus;
import com.workspace.coupon_api.application.exception.CouponAlreadyDeletedException;
import com.workspace.coupon_api.application.exception.InvalidCouponCodeException;
import com.workspace.coupon_api.application.exception.InvalidCouponDiscountException;
import com.workspace.coupon_api.application.exception.InvalidExpirationDateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CouponApplicationServiceIntegrationTest {

    @Autowired
    private CouponApplicationService couponApplicationService;

    @Test
    void createCoupon_persistsAndReturnsResponse() {
        CouponRequest request = new CouponRequest(
                "ABC123",
                "Integration test coupon",
                new BigDecimal("1.00"),
                LocalDateTime.now().plusDays(1),
                true
        );

        CouponResponse response = couponApplicationService.createCoupon(request);
        assertNotNull(response.id());
        assertEquals("ABC123", response.code());
        assertEquals(new BigDecimal("1.00"), response.discountValue());
        assertEquals(CouponStatus.ACTIVE, response.status());

        CouponResponse detail = couponApplicationService.detailCoupon(response.id());
        assertEquals(response.id(), detail.id());
        assertEquals(CouponStatus.ACTIVE, detail.status());
    }

    @Test
    void createCoupon_withExpirationInPast_throws() {
        CouponRequest request = new CouponRequest(
                "ABC123",
                "expired coupon",
                new BigDecimal("1.00"),
                LocalDateTime.now().minusDays(1),
                false
        );

        assertThrows(InvalidExpirationDateException.class, () -> couponApplicationService.createCoupon(request));
    }

    @Test
    void createCoupon_withInvalidCode_throws() {
        CouponRequest request = new CouponRequest(
                "AB-12",
                "invalid code",
                new BigDecimal("1.00"),
                LocalDateTime.now().plusDays(1),
                false
        );

        assertThrows(InvalidCouponCodeException.class, () -> couponApplicationService.createCoupon(request));
    }

    @Test
    void createCoupon_withInvalidDiscount_throws() {
        CouponRequest request = new CouponRequest(
                "ABC123",
                "invalid discount",
                new BigDecimal("0.49"),
                LocalDateTime.now().plusDays(1),
                false
        );

        assertThrows(InvalidCouponDiscountException.class, () -> couponApplicationService.createCoupon(request));
    }

    @Test
    void deleteCoupon_marksDeleted_andCannotDeleteTwice() {
        CouponRequest request = new CouponRequest(
                "ABC123",
                "to be deleted",
                new BigDecimal("1.00"),
                LocalDateTime.now().plusDays(1),
                false
        );

        CouponResponse created = couponApplicationService.createCoupon(request);
        assertEquals(CouponStatus.INACTIVE, created.status());

        couponApplicationService.deleteCoupon(created.id());
        CouponResponse afterDelete = couponApplicationService.detailCoupon(created.id());
        assertEquals(CouponStatus.DELETED, afterDelete.status());
        assertNotNull(afterDelete.deletedAt());

        assertThrows(CouponAlreadyDeletedException.class, () -> couponApplicationService.deleteCoupon(created.id()));
    }
}

