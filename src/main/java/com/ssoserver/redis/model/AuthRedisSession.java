package com.ssoserver.redis.model;

//import com.ssoserver.model.entity.AdminUser;
import com.ssoserver.model.UserDetail;
import com.ssoserver.model.entity.User;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class AuthRedisSession implements Serializable {

    private String accessToken;
    private String refreshToken;
    private UserDetail user;
    private long expireTime; // millisecond
    private Date createdDate;

    public AuthRedisSession() {
    }

    public AuthRedisSession(String accessToken, String refreshToken, UserDetail user, long expireTime, Date createdDate) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
        this.expireTime = expireTime;
        this.createdDate = createdDate;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public UserDetail getUser() {
        return user;
    }

    public void setUser(UserDetail user) {
        this.user = user;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
