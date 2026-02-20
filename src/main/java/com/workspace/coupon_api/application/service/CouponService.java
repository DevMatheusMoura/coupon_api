package com.workspace.coupon_api.application.service;

import com.workspace.coupon_api.application.api.CouponRequest;
import com.workspace.coupon_api.application.api.CouponResponse;

import java.util.UUID;

public interface CouponService {
    CouponResponse createCoupon(CouponRequest couponRequest);
    CouponResponse detailCoupon(UUID id);
    void deleteCoupon(UUID id);
}

