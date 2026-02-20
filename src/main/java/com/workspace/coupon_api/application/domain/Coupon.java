package com.workspace.coupon_api.application.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "coupon")

public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Embedded
    private CouponCode code;

    @Column(name = "description", nullable = false)
    private String description;

    @Embedded
    private CouponDiscount discount;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "expirationDate", nullable = false)
    private LocalDateTime expirationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CouponStatus status;

    @Column(name = "published", nullable = false)
    private Boolean published = false;

    @Column(name = "redeemed", nullable = false)
    private Boolean redeemed = false;

    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;

    public Coupon(CouponCode code, String description, CouponDiscount discount, LocalDateTime expirationDate) {
        this.code = code;
        this.description = description;
        this.discount = discount;
        this.createdAt = LocalDateTime.now();
        checkIfExpirationDateAsPassed(expirationDate);
        this.expirationDate = expirationDate;
        this.status = CouponStatus.INACTIVE;
        this.status = updateStatusFromPublished(published);
    }

    public Coupon(@NotBlank String code, @NotBlank String description, @NotNull @Positive BigDecimal bigDecimal, @NotNull LocalDateTime localDateTime, @NotNull Boolean published) {
    }

    private void checkIfExpirationDateAsPassed(LocalDateTime expirationDate) {
        if (expirationDate.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("A data de expiração não deve estar no passado");
        }
    }

    private CouponStatus updateStatusFromPublished(Boolean published) {
        return Boolean.TRUE.equals(published) ? CouponStatus.ACTIVE : CouponStatus.INACTIVE;
    }

    public void changeToDelete() {
        throwIfDeleted();
        this.status = CouponStatus.DELETED;
        this.deletedAt = LocalDateTime.now();
    }

    private void throwIfDeleted() {
        if (this.status.equals(CouponStatus.DELETED)) {
            throw new RuntimeException("Coupon already deleted!");
        }
    }
}
