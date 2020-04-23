package com.muscidae.parrot.common.entity.result;

/**
 * @author muscidae
 * @date 2019/1/1 10:11
 * @description Api接口全局status码, message说明.
 */
public enum ResultStatus {

    FAIL(0, "fail"),

    SUCCESS(200, "success"),

    UNAUTHORIZED(401, "unauthorized"),
    TOKEN_EXPIRED(444, "token expired"),

    ERROR(500, "server error"),

    FUSING(601, "service temporarily unavailable"),


    ;

    private final int status;

    private final String message;

    ResultStatus(int status, String message){
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
