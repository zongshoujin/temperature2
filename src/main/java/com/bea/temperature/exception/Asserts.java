package com.bea.temperature.exception;

/**
 * Created by zsj on 2021/7/8.
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }
}
