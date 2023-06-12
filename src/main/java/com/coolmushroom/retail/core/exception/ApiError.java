package com.coolmushroom.retail.core.exception;

public class ApiError extends Exception {
    private String error;
    private String message;
    private Integer status;

    public ApiError(String error, String message, Integer status) {
        this.error = error;
        this.message = message;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public Integer getStatus() {
        return status;
    }
}
