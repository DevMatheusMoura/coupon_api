package com.workspace.coupon_api.application.infra;

import com.workspace.coupon_api.application.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CouponSpringDataJPARepository extends JpaRepository<Coupon, UUID> {
    Optional<Coupon> findCouponById(UUID id);
}