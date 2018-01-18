package com.ssoserver.auth;

import com.ssoserver.model.UserDetail;
import com.ssoserver.model.request.AuthRequest;
import com.ssoserver.model.response.AuthResponse;
import com.ssoserver.model.response.Response;
import com.ssoserver.model.response.ResponseGenerator;
import com.ssoserver.redis.model.AuthRedisSession;
import com.ssoserver.redis.service.RedisSessionService;
import com.ssoserver.security.JwtTokenUtil;
import com.ssoserver.service.UserDetailService;
import com.ssoserver.utils.Constant;
import com.ssoserver.utils.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class OAuth2API {

    private static final Logger LOGGER = LoggerFactory.getLogger(OAuth2API.class);
    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private RedisSessionService redisSessionService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value="token", method = RequestMethod.POST)
    public ResponseEntity<?> authorize(@RequestBody AuthRequest authRequest) throws AuthenticationException {
        UserDetail userDetail = userDetailService.authorizeUser(authRequest);
        if (!userDetail.isAuthenticated()) {
            Response response = new Response(400, "Invalid user/password", null);
            return new ResponseGenerator().buildResponse(response);
        }

        // generate access token
        String accessToken = TokenUtil.genrateJwtAccessToken(userDetail);
        String refreshToken = TokenUtil.generateRefreshToken(userDetail);
        // save to the database (redis)
        Date now = new Date();
        Calendar expiredAt = Calendar.getInstance();
        expiredAt.setTimeInMillis(now.getTime() + Constant.DEFAULT_EXPIRE_TIME);
        AuthRedisSession session = new AuthRedisSession(accessToken, refreshToken, userDetail, expiredAt.getTimeInMillis(), now);
        redisSessionService.save(session);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken(accessToken);
        authResponse.setRefreshToken(refreshToken);
        authResponse.setTokenType(Constant.AUTH_TYPE_BEARER);
        authResponse.setPublicKey("");
        Response response = new Response(200, "", authResponse);
        return new ResponseGenerator().buildResponse(response);

    }

    @RequestMapping(value = "refresh_token", method = RequestMethod.POST)
    public ResponseEntity<Response> refreshToken(
            @RequestParam(value = "grant_type", defaultValue = "refresh_token", required = false) String grantType,
            @RequestParam(value= "refresh_token") String refreshToken,
            @RequestParam(value="access_token") String accessToken
    ) {
        // validate
        if (null == refreshToken) {
            return null;
        }


        AuthRedisSession authRedisSession = redisSessionService.findByAccessToken(accessToken);
        if (!refreshToken.equalsIgnoreCase(authRedisSession.getRefreshToken())) {
            return null;
        }
        String accessTokenNew = TokenUtil.genrateJwtAccessToken(authRedisSession.getUser());
        String refreshTokenNew = TokenUtil.generateRefreshToken(authRedisSession.getUser());

        // delete in redis
        redisSessionService.validateAndDelete(accessToken);

        // save to the database (redis)
        Date now = new Date();
        Calendar expiredAt = Calendar.getInstance();
        expiredAt.setTimeInMillis(now.getTime() + Constant.DEFAULT_EXPIRE_TIME);
        AuthRedisSession session = new AuthRedisSession(accessToken, refreshToken, authRedisSession.getUser(), expiredAt.getTimeInMillis(), now);
        redisSessionService.save(session);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken(accessToken);
        authResponse.setRefreshToken(refreshToken);
        authResponse.setTokenType(Constant.AUTH_TYPE_BEARER);
        authResponse.setPublicKey("");
        Response response = new Response(200, "", authResponse);
        return new ResponseGenerator().buildResponse(response);

    }
}