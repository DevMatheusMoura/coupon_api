package com.workspace.coupon_api.application.api;

import com.workspace.coupon_api.application.domain.Coupon;
import com.workspace.coupon_api.application.domain.CouponStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CouponResponse(

        UUID id,
        String code,
        String description,
        BigDecimal discountValue,
        LocalDateTime createdAt,
        LocalDateTime expirationDate,
        LocalDateTime deletedAt,
        CouponStatus status,
        Boolean published,
        Boolean redeemed
) {
    public CouponResponse(Coupon coupon) {
        this(
                coupon.getId(),
                coupon.getCode().getCode(),
                coupon.getDescription(),
                coupon.getDiscount().getValue(),
                coupon.getCreatedAt(),
                coupon.getExpirationDate(),
                coupon.getDeletedAt(),
                coupon.getStatus(),
                coupon.getPublished(),
                coupon.getRedeemed()
        );

    }
}

