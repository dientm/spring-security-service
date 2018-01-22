package com.ssoserver.utils;

import com.ssoserver.model.UserDetail;
import com.ssoserver.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class TokenUtil {

    public static String genrateJwtAccessToken(UserDetail userDetail) throws Exception {
        return new JwtTokenUtil().generateToken(userDetail);
    }

    public static String generateRefreshToken(UserDetail userDetail) {
        return CommonUtil.generateUUID();
    }
}
