package com.mysite.stockburning.util;

import lombok.Getter;

@Getter
public enum UserRole {
    MANAGER("ROLE_MANAGER"), //userRole.MANAGER 은 ROLE_MANAGER 로 저장
    USER("ROLE_USER");       //UserRole.USER 은 ROLE_USER 로 저장

    private final String value;
    UserRole(String value) {
        this.value = value;
    }
}

