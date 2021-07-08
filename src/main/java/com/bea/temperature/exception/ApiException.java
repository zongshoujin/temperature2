package com.bea.temperature.exception;

/**
 * Created by zsj on 2021/7/8.
 */
public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
