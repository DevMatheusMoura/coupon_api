package com.workspace.coupon_api.application.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/coupons")
public interface CouponAPI {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CouponResponse createCoupon(@RequestBody @Valid CouponRequest couponRequest);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    CouponResponse detailCoupon(@PathVariable UUID id);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCoupon(@PathVariable UUID id);

}
