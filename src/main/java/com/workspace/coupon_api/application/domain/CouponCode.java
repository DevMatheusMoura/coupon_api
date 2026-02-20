package com.workspace.coupon_api.application.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponCode {

    @Column(name = "code", length = 6, nullable = false)
    private String code;

    public CouponCode(String code) {
        String codeFormated = removeEspecialCharacters(code);
        validateCode(codeFormated);
        this.code = codeFormated;
    }

    private void validateCode(String code) {
        checkIfCodeIsBlanckOrNull(code);
        checkSizeOfTheCode(code);
    }

    private static final Integer CODE_SIZE = 6;

    private void checkIfCodeIsBlanckOrNull(String code) {
        if (code.isBlank()) {
            throw new RuntimeException("O código não deve estar vazio");
        }
    }

    private void checkSizeOfTheCode(String code) {
        if (code.length() != CODE_SIZE) {
            throw new RuntimeException("O código deve ter o seu tamanho igual a " + CODE_SIZE);
        }
    }

    private String removeEspecialCharacters(String code) {
        return code.replaceAll("[^a-zA-Z0-9]", "");
    }
}