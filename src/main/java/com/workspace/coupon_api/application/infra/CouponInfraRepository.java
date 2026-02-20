package com.workspace.coupon_api.application.infra;

import com.workspace.coupon_api.application.domain.Coupon;
import com.workspace.coupon_api.application.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Log4j2
@Repository
@RequiredArgsConstructor
public class CouponInfraRepository implements CouponRepository {

    private final CouponSpringDataJPARepository couponSpringDataJPARepository;

    @Override
    public Coupon save(Coupon coupon) {
        log.info("[start] CouponInfraRepository - save");
        couponSpringDataJPARepository.save(coupon);
        log.debug("[finish] CouponInfraRepository - save");
        return coupon;
    }

    @Override
    public Coupon findCouponById(UUID id) {
        log.info("[start] CouponInfraRepository - findCouponById");
        Coupon couponFinded = couponSpringDataJPARepository.findCouponById(id)
                .orElseThrow(() -> new RuntimeException("Coupon não encontrado!"));
        log.debug("[finish] CouponInfraRepository - findCouponById");
        return couponFinded;
    }
}
