package com.salon.custom.exception;

public class InvalidRefreshTokenException extends RuntimeException {

    public InvalidRefreshTokenException(String msg){
        super(msg);
    }

    public InvalidRefreshTokenException(){
    }
}
