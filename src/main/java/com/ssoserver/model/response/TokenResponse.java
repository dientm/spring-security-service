package com.ssoserver.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class TokenResponse implements Serializable {
    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private String publicKey;

}
