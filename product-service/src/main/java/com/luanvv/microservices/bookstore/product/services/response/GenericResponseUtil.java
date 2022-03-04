package com.luanvv.microservices.bookstore.product.services.response;

import com.luanvv.bookstore.product.specs.model.GenericResponse;
import lombok.Getter;

@Getter
public class GenericResponseUtil<T> {
    private String code;
    private String message;
    private T data;

    public static <T> GenericResponse ok(String message, T data) {
        return new GenericResponse()
            .code("0")
            .message(message)
            .data(data);
    }

    public static <T> GenericResponse ok(String message) {
        return ok(message, null);
    }

    public static <T> GenericResponse error(String message, T data) {
        return new GenericResponse()
            .code("1")
            .message(message)
            .data(data);
    }

    public static <T> GenericResponse error(String message) {
        return error(message, null);
    }
}
