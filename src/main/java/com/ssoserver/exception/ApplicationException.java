package com.ssoserver.exception;

public class ApplicationException extends RuntimeException {
    private String message;
    public ApplicationException() {

    }
    public ApplicationException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
