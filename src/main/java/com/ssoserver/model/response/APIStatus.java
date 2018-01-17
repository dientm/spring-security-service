package com.ssoserver.model.response;

public enum APIStatus {

    OK(200, "OK"),
    //////////////////
    // CLIENT SIDE  //
    //////////////////
    ERR_BAD_REQUEST(400, "Bad request"),
    ERR_UNAUTHORIZED(401, "Unauthorized or Access Token is expired"),
    ERR_FORBIDDEN(403, "Forbidden! Access denied"),
    ERR_BAD_PARAMS(406, "Bad parameters"),
    //////////////////
    // SERVER SIDE  //
    //////////////////
    ERR_INTERNAL_SERVER(500, "Internal Server Error"),
    ERR_CREATE_MODEL(501, "Create model error"),
    //////////////////
    // SESSION SIDE //
    //////////////////
    ERR_TOKEN_NOT_FOUND(600, "Access token not found"),
    ERR_INVALID_TOKEN(601, "Access token is invalid"),
    ERR_TOKEN_EXPIRED(602, "Access token is expired"),
    ERR_INVALID_REFRESH_TOKEN(603, "Refresh token is invalid"),
    ERR__REFRESH_TOKEN(604, "Refresh token error");

    private final int code;
    private final String description;

    private APIStatus(int s, String v) {
        code = s;
        description = v;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
