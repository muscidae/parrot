package com.muscidae.parrot.common.basic.exception;

/**
 * @author muscidae
 * @date 2019/1/21 11:35
 * @description 业务处理中非预期的异常,出现此异常说明系统不能正常处理
 * @description 不需捕获处理,主要用户java封装中有异常抛出但实际中确定没有异常的替代
 */
public class UnexpectedException extends RuntimeException{

    /**
     * 异常状态码
     */
    private int status;

    public UnexpectedException() {
        super();
    }

    /**
     * @param message 异常信息
     */
    public UnexpectedException(String message) {
        super(message);
    }

    /**
     * @param status 异常状态码
     * @param message 异常信息
     */
    public UnexpectedException(int status, String message) {
        super(message);
        this.status = status;
    }

    /**
     * @param cause 来源异常
     */
    public UnexpectedException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message 异常信息
     * @param cause  来源异常
     */
    public UnexpectedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param status 异常状态码
     * @param message 异常信息
     * @param cause  来源异常
     */
    public UnexpectedException(int status, String message, Throwable cause) {
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
