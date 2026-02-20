package com.workspace.coupon_api.application.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class CouponDiscount {

    @Column(name = "discount_value", precision = 10, scale = 2, nullable = false)
        private BigDecimal value;

    public CouponDiscount(BigDecimal value) {
            checkIfIsLessThanMinimumDiscount(value);
            this.value = value;
        }
        private static final BigDecimal MINIMUM_DISCOUNT = new BigDecimal("0.5");

        private void checkIfIsLessThanMinimumDiscount(BigDecimal value) {
            if (value.compareTo(MINIMUM_DISCOUNT) < 0) {
                throw new RuntimeException("O valor minimo de desconto é: " + MINIMUM_DISCOUNT);
            }
        }
}
