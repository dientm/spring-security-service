package com.ssoserver.redis.model;

//import com.ssoserver.model.entity.AdminUser;
import com.ssoserver.model.entity.User;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class RedisSession implements Serializable {

    private String accessToken;
    private String refreshToken;
//    private AdminUser admin;
    private User user;
//    private List<AppRole> userRoles;
    private long expireTime; // millisecond
    private Date createdDate;

    public RedisSession() {
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

//    public AdminUser getAdmin() {
//        return admin;
//    }

//    public void setAdmin(AdminUser admin) {
//        this.admin = admin;
//    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.accessToken);
        hash = 61 * hash + Objects.hashCode(this.refreshToken);
//        hash = 61 * hash + Objects.hashCode(this.admin);
        hash = 61 * hash + Objects.hashCode(this.user);
        hash = 61 * hash + (int) (this.expireTime ^ (this.expireTime >>> 32));
        hash = 61 * hash + Objects.hashCode(this.createdDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RedisSession other = (RedisSession) obj;
        if (this.expireTime != other.expireTime) {
            return false;
        }
        if (!Objects.equals(this.accessToken, other.accessToken)) {
            return false;
        }
        if (!Objects.equals(this.refreshToken, other.refreshToken)) {
            return false;
        }
/*        if (!Objects.equals(this.admin, other.admin)) {
            return false;
        }*/
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.createdDate, other.createdDate)) {
            return false;
        }
        return true;
    }

}
