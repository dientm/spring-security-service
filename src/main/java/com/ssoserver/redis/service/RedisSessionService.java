package com.ssoserver.redis.service;

import com.ssoserver.exception.ApplicationException;
import com.ssoserver.redis.model.AuthRedisSession;
import com.ssoserver.redis.repository.RedisSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class RedisSessionService {

    @Autowired
    private RedisSessionRepository redisSessionRepository;

    public void save(AuthRedisSession session) {
        redisSessionRepository.save(session);
    }
    public AuthRedisSession findByAccessToken(String accessToken) {
        AuthRedisSession session = redisSessionRepository.findOne(accessToken);
        // validate already exist session
        if (session != null) {
            // validate session expire time
            if (session.getExpireTime() - System.currentTimeMillis()/ 1000 <= 0) {
                throw new ApplicationException("Token has been expired");
            }

        } else {
            throw new ApplicationException("Token not found");
        }
        return session;
    }

    /**
     * Validate and delete session
     *
     * @param accessToken
     * @throws ApplicationException if accessToken not found
     */
    public void validateAndDelete(String accessToken) {

        AuthRedisSession session = redisSessionRepository.findOne(accessToken);
        // validate already exist session
        if (session != null) {
            // clear session
            redisSessionRepository.delete(accessToken);
        } else {
            throw new ApplicationException("Token not found");
        }
    }

}
