package com.scau.ruan.hotel.enums;

import lombok.Getter;

/**
 * Create By  @林俊杰
 * 2021/5/15 21:01
 *
 * @version 1.0
 */
@Getter
public enum UserTypeEnums {
    UNACTIVATE_USER(0,"未激活用户"),

    ORDINARY_USER(1,"普通用户"),

    VIP_USER(2,"VIP用户"),
    ;


    private Integer code;

    private String message;

    UserTypeEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
