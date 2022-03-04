package com.salon.custom.exception;

public class BaseException extends RuntimeException{
    private String message;
    private int code;
    private boolean status;

    public BaseException() {
        super();
    }

    public BaseException(String message, int code) {
        super();
        this.message = message;
        this.code = code;
    }

    public BaseException(String message, int code, boolean status) {
        super();
        this.message = message;
        this.code = code;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}