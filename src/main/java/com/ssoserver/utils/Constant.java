package com.ssoserver.utils;

import java.util.List;

public interface Constant {


    String AUTH_TYPE_BEARER = "Bearer";
    long DEFAULT_EXPIRE_TIME = 3600L;
    String REDIS_SESSION_KEY = "session";

    enum ErrorCode {
        INVALID_TOKEN("INVALID_TOKEN", "Invalid access_token/refresh_token"),
        INVALID_USER_CREDENTIAL("INVALID_USER_CRED", "Invalid username/password");


        private final String name;
        private final String desc;
        ErrorCode(String name, String desc) {
            this.name = name;
            this.desc = desc;
        }
        public String getName() {
            return name;
        }

        public String getDesc() {
            return desc;
        }
        }


}
