package com.muscidae.parrot.gateway.exception.auth;

/**
 * @author muscidae
 * @date 2020/3/14 14:02
 * @copyright ©2020
 * @description 鉴权异常
 */
public class AuthException extends RuntimeException {

    public AuthException() {
        super();
    }

    public AuthException(String message) {
        super(message);
    }

    public AuthException(Throwable cause) {
        super(cause);
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }

}
