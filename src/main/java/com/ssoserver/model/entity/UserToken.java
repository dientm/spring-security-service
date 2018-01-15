package com.ssoserver.model.entity;

import java.io.Serializable;
import java.util.Date;

public class UserToken implements Serializable {
    private UserTokenId userTokenId;
    private String refreshToken;
    private Long appId;
    private Date expireTime;
    private Date createdDate;

}
