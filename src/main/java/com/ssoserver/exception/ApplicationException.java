package com.ssoserver.exception;

import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException{
    private String message;

    public ApplicationException(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
