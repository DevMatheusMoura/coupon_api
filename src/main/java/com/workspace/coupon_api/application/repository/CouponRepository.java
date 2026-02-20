package com.workspace.coupon_api.application.repository;

import com.workspace.coupon_api.application.domain.Coupon;

import java.util.UUID;

public interface CouponRepository {
    void save(Coupon couponCreated);
    Coupon findCouponById(UUID id);
}
