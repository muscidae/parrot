package com.muscidae.parrot.common.entity.result;

/**
 * @author muscidae
 * @date 2019/1/1 10:06
 * @description API通用返回封装类
 */
public class ApiResult<T> {

    protected int status;

    protected String message;

    protected T data;

    protected String error;

    protected String path;

    private long timestamp;

    protected ApiResult(){ }

    private ApiResult(Builder<T> builder){
        this.status = builder.status;
        this.message = builder.message;
        this.data = builder.data;
        this.error = builder.error;
        this.path = builder.path;
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ApiResult<T> success() { return new Builder<T>().setStatus(ResultStatus.SUCCESS.getStatus()).setMessage(ResultStatus.SUCCESS.getMessage()).build(); }

    public static <T> ApiResult<T> success(int status) { return new Builder<T>().setStatus(status).setMessage(ResultStatus.SUCCESS.getMessage()).build(); }

    public static <T> ApiResult<T> success(String message) { return new Builder<T>().setStatus(ResultStatus.SUCCESS.getStatus()).setMessage(message).build(); }

    public static <T> ApiResult<T> success(T data) { return new Builder<T>().setStatus(ResultStatus.SUCCESS.getStatus()).setMessage(ResultStatus.SUCCESS.getMessage()).setData(data).build(); }

    public static <T> ApiResult<T> success(int status, String message) { return new Builder<T>().setStatus(status).setMessage(message).build(); }

    public static <T> ApiResult<T> fail() { return new Builder<T>().setStatus(ResultStatus.FAIL.getStatus()).setMessage(ResultStatus.FAIL.getMessage()).build(); }

    public static <T> ApiResult<T> fail(int status) { return new Builder<T>().setStatus(status).setMessage(ResultStatus.FAIL.getMessage()).build(); }

    public static <T> ApiResult<T> fail(String message) { return new Builder<T>().setStatus(ResultStatus.FAIL.getStatus()).setMessage(message).build(); }

    public static <T> ApiResult<T> fail(int status, String message) { return new Builder<T>().setStatus(status).setMessage(message).build(); }

    public static <T> ApiResult<T> fail(int status, String message, String error) { return new Builder<T>().setStatus(status).setMessage(message).setError(error).build(); }

    public static <T> ApiResult<T> fail(int status, String message, String error, String path) { return new Builder<T>().setStatus(status).setMessage(message).setError(error).setPath(path).build(); }

    public static <T> ApiResult<T> error() { return new Builder<T>().setStatus(ResultStatus.ERROR.getStatus()).setMessage(ResultStatus.ERROR.getMessage()).build(); }

    public static <T> ApiResult<T> error(int status) { return new Builder<T>().setStatus(ResultStatus.ERROR.getStatus()).build(); }

    public static <T> ApiResult<T> error(String message) { return new Builder<T>().setStatus(ResultStatus.ERROR.getStatus()).setMessage(message).build(); }

    public static <T> ApiResult<T> error(int code, String message) { return new Builder<T>().setStatus(code).setMessage(message).build(); }

    public static <T> ApiResult<T> error(int code, String message, String error) { return new Builder<T>().setStatus(code).setMessage(message).setError(error).build(); }

    public static <T> ApiResult<T> error(int code, String message, String error, String path) { return new Builder<T>().setStatus(code).setMessage(message).setError(error).setPath(path).build(); }

    public int getStatus() { return status; }

    public String getMessage() { return message; }

    public T getData() { return data; }

    public String getError() { return error; }

    public String getPath() { return path; }

    public long getTimestamp() { return timestamp; }

    public static class Builder<T> extends ApiResult<T> {
        public Builder<T> setStatus(int status) { this.status = status; return this; }
        public Builder<T> setMessage(String message) { this.message = message; return this; }
        public Builder<T> setData(T data) { this.data = data; return this; }
        public Builder<T> setError(String error) { this.error = error; return this; }
        public Builder<T> setPath(String path) { this.path = path; return this; }
        public ApiResult<T> build(){ return new ApiResult<>(this); }
    }

}
