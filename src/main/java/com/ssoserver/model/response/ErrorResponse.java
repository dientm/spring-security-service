package com.ssoserver.model.response;

import com.ssoserver.utils.Constant;

public class ErrorResponse {
    private String error;
    private String message;

    public ErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public ErrorResponse() {
    }
    public ErrorResponse(Constant.ErrorCode errorCode) {
        this.error = errorCode.getName();
        this.message = errorCode.getDesc();
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
