package com.workspace.coupon_api.application.service;

import com.workspace.coupon_api.application.api.CouponRequest;
import com.workspace.coupon_api.application.api.CouponResponse;
import com.workspace.coupon_api.application.domain.Coupon;
import com.workspace.coupon_api.application.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class CouponApplicationService implements CouponService {


    private final com.workspace.coupon_api.application.repository.CouponRepository couponRepository;

    @Override
    public CouponResponse createCoupon(CouponRequest couponRequest) {
        log.info("[start] CouponApplicationService - createCoupon");
        Coupon couponCreated = new Coupon(couponRequest.code(), couponRequest.description(),
                couponRequest.discountValue(), couponRequest.expirationDate(), couponRequest.published());
        couponRepository.save(couponCreated);
        log.debug("[finish] CouponApplicationService - createCoupon");
        return new CouponResponse(couponCreated);
    }



    @Override
    public CouponResponse detailCoupon(UUID id) {
        log.info("[start] CouponApplicationService - detailCoupon");
        Coupon couponFinded = couponRepository.findCouponById(id);
        log.debug("[finish] CouponApplicationService - detailCoupon");
        return new CouponResponse(couponFinded);
    }

    @Override
    public void deleteCoupon(UUID id) {
        log.info("[start] CouponApplicationService - deleteCoupon");
        Coupon couponFinded = couponRepository.findCouponById(id);
        couponFinded.changeToDelete();
        couponRepository.save(couponFinded);
        log.debug("[finish] CouponApplicationService - deleteCoupon");
    }
}

