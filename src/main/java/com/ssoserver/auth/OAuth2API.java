package com.ssoserver.auth;

import com.ssoserver.exception.ApplicationException;
import com.ssoserver.model.UserDetail;
import com.ssoserver.model.request.AuthRequest;
import com.ssoserver.model.response.AuthResponse;
import com.ssoserver.model.response.ErrorResponse;
import com.ssoserver.redis.model.AuthRedisSession;
import com.ssoserver.redis.service.RedisSessionService;
import com.ssoserver.security.JwtTokenUtil;
import com.ssoserver.service.UserDetailService;
import com.ssoserver.utils.Constant;
import com.ssoserver.utils.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public ResponseEntity<?> authorize(@RequestBody AuthRequest authRequest){
        AuthResponse authResponse = new AuthResponse();
        try {
            UserDetail userDetail = userDetailService.authorizeUser(authRequest);
            if (!userDetail.isAuthenticated()) {
                throw new ApplicationException("Invalid username/password");
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


            authResponse.setAccessToken(accessToken);
            authResponse.setRefreshToken(refreshToken);
            authResponse.setTokenType(Constant.AUTH_TYPE_BEARER);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity( new ErrorResponse(Constant.ErrorCode.INVALID_USER_CREDENTIAL), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorResponse(Constant.ErrorCode.INVALID_TOKEN), HttpStatus.SERVICE_UNAVAILABLE);
        }


        return new ResponseEntity(authResponse, HttpStatus.OK);

    }

    @RequestMapping(value = "refresh_token", method = RequestMethod.POST)
    public ResponseEntity<?> refreshToken(@RequestBody AuthRequest authRequest) {

        try {
            if (null == authRequest.getAccessToken() || null == authRequest.getRefreshToken()) {
                throw new ApplicationException("null access_token or refresh_token");
            }
            AuthRedisSession authRedisSession = redisSessionService.findByAccessToken(authRequest.getAccessToken());

            if (!authRequest.getRefreshToken().equalsIgnoreCase(authRedisSession.getRefreshToken())) {
                throw new ApplicationException("refresh_token is not existed in server");
            }
            String accessTokenNew = TokenUtil.genrateJwtAccessToken(authRedisSession.getUser());
            String refreshTokenNew = TokenUtil.generateRefreshToken(authRedisSession.getUser());
            // delete in redis
            redisSessionService.validateAndDelete(authRequest.getAccessToken());

            // save to the database (redis)
            Date now = new Date();
            Calendar expiredAt = Calendar.getInstance();
            expiredAt.setTimeInMillis(now.getTime() + Constant.DEFAULT_EXPIRE_TIME);
            AuthRedisSession session = new AuthRedisSession(accessTokenNew, refreshTokenNew, authRedisSession.getUser(), expiredAt.getTimeInMillis(), now);
            redisSessionService.save(session);

            AuthResponse authResponse = new AuthResponse();
            authResponse.setAccessToken(accessTokenNew);
            authResponse.setRefreshToken(refreshTokenNew);
            authResponse.setTokenType(Constant.AUTH_TYPE_BEARER);
            return new ResponseEntity(authResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorResponse(Constant.ErrorCode.INVALID_TOKEN), HttpStatus.UNAUTHORIZED);
        }


    }
    @RequestMapping(value="token", method = RequestMethod.DELETE)
    public ResponseEntity<?> revoke(@RequestBody AuthRequest authRequest) {
        try {
            if (redisSessionService.findByAccessToken(authRequest.getAccessToken()).getUser().getAppId().equalsIgnoreCase(authRequest.getClientId())) {
                redisSessionService.validateAndDelete(authRequest.getAccessToken());
            }
        } catch (ApplicationException e) {
            return new ResponseEntity(new ErrorResponse("INVALID_TOKEN", "Invalid Token"), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value="validate", method = RequestMethod.POST)
    public ResponseEntity<?> validate(@RequestBody AuthRequest authRequest) {
        try {
            redisSessionService.findByAccessToken(authRequest.getAccessToken());
        } catch (Exception e) {
            return new ResponseEntity(new ErrorResponse(Constant.ErrorCode.INVALID_TOKEN), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}