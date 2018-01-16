package com.ssoserver.redis.service;

import com.ssoserver.api.response.APIStatus;
import com.ssoserver.exception.ApplicationException;
import com.ssoserver.redis.model.RedisSession;
import com.ssoserver.redis.repository.RedisSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedisSessionService {

    @Autowired
    private RedisSessionRepository redisSessionRepository;

    public void save(RedisSession session) {
        redisSessionRepository.save(session);
    }

    /**
     * Find session stored in Redis and validate expire time
     *
     * @param accessToken
     *
     * @throws ApplicationException with ResultCode are (INVALID_TOKEN,
     * TOKEN_EXPIRED)
     *
     * @return A RedisSession model, never return null
     */
    public RedisSession findAndValidate(String accessToken) throws ApplicationException {

        RedisSession session = redisSessionRepository.findOne(accessToken);
        // validate already exist session
        if (session != null) {

            // validate session expire time
            if (session.getExpireTime() - System.currentTimeMillis() <= 0) {
                throw new ApplicationException(APIStatus.ERR_TOKEN_EXPIRED);
            }

        } else {
            throw new ApplicationException(APIStatus.ERR_INVALID_TOKEN);
        }

        return session;
    }

    /**
     * Validate and delete session
     *
     * @param accessToken
     * @throws ApplicationException if accessToken not found
     */
    public void validateAndDelete(String accessToken) throws ApplicationException {

        RedisSession session = redisSessionRepository.findOne(accessToken);
        // validate already exist session
        if (session != null) {

            // clear session
            redisSessionRepository.delete(accessToken);

        } else {
            throw new ApplicationException(APIStatus.ERR_INVALID_TOKEN);
        }
    }

    public RedisSession findByAccessToken(String accessToken) {
        return redisSessionRepository.findOne(accessToken);
    }

}
