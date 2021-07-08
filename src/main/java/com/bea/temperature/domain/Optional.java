package com.bea.temperature.domain;

/**
 * Created by zsj on 2021/7/8.
 */
public class Optional<T> {

    private long code;

    private String message;

    private T data;

    protected Optional() {
    }

    protected Optional(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Optional<T> success(T data) {
        return new Optional<T>(200, "success", data);
    }

    public static <T> Optional<T> failed(String msg, T data) {
        return new Optional<T>(-200, msg, data);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
