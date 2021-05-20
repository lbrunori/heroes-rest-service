package com.w2m.exception;

public class NotFoundException extends RuntimeException {
    private String message;
    private String code;

    public NotFoundException(String code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
