package com.workspace.coupon_api.application.api;

import com.workspace.coupon_api.application.service.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Log4j2
@RequiredArgsConstructor

public class CouponController implements CouponAPI {

    private final CouponService couponService;

    @Override
    public CouponResponse createCoupon(CouponRequest couponRequest) {
        log.info("[start] CouponController - createCoupon");
        CouponResponse couponCreated = couponService.createCoupon(couponRequest);
        log.debug("[finish] CouponController - createCoupon");
        return couponCreated;
    }

    @Override
    public CouponResponse detailCoupon(UUID id) {
        log.info("[start] CouponController - detailCoupon");
        CouponResponse detailedCoupon = couponService.detailCoupon(id);
        log.debug("[finish] CouponController - detailCoupon");
        return detailedCoupon;
    }

    @Override
    public void deleteCoupon(UUID id) {
        log.info("[start] CouponController - deleteCoupon");
        couponService.deleteCoupon(id);
        log.debug("[finish] CouponController - deleteCoupon");
    }
}


