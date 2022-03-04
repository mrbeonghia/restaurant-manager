package com.salon.custom.dto.base;

public class BaseResponse {
    protected Boolean isSuccess;
    protected String message;
    protected int code;

    public BaseResponse(){
        setSuccess(Boolean.TRUE);
    }

    public BaseResponse(String message, int code) {
        setSuccess(Boolean.FALSE);
        setMessage(message);
        setCode(code);
    }

    public BaseResponse(String message, Boolean isSuccess, int code) {
        setSuccess(isSuccess);
        setMessage(message);
        setCode(code);
    }



    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
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
}
