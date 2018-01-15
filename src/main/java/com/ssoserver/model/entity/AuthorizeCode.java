package com.ssoserver.model.entity;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

//@Entity
public class AuthorizeCode implements Serializable {
    private String authCode;
    private String clientId;
    private String userId;
    private String state;
    private long expireTime;
    private Date createDate;

}
