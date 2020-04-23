package com.muscidae.parrot.common.basic.exception;

/**
 * @author muscidae
 * @date 2019/1/22 12:20
 * @description 业务异常
 */
public class BusinessException extends RuntimeException{

    /**
     * 异常状态码
     */
    private int status;

    public BusinessException() {
        super();
    }

    /**
     * @param: message 异常信息
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * @param: status 异常状态码
     * @param: message 异常信息
     */
    public BusinessException(int status, String message) {
        super(message);
        this.status = status;
    }

    /**
     * 构造函数
     * @param cause 来源异常
     */
    public BusinessException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message 异常信息
     * @param cause  来源异常
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param status 异常状态码
     * @param message 异常信息
     * @param cause  来源异常
     */
    public BusinessException(int status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
