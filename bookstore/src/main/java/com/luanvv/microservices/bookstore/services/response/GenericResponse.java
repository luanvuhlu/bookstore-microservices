package com.luanvv.microservices.bookstore.services.response;

import lombok.Getter;

@Getter
public class GenericResponse<T> {
    private String code;
    private String message;
    private T data;

    private GenericResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> GenericResponse ok(String message, T data) {
        return new GenericResponse("0", message, data);
    }

    public static <T> GenericResponse ok(String message) {
        return ok(message, null);
    }

    public static <T> GenericResponse error(String message, T data) {
        return new GenericResponse("1", message, data);
    }

    public static <T> GenericResponse error(String message) {
        return error(message, null);
    }
}
