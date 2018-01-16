package com.ssoserver.redis.repository;

import com.ssoserver.redis.model.RedisSession;
import com.ssoserver.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class RedisSessionRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public RedisSessionRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    public void save(RedisSession session) {
        hashOperations.put(Constant.REDIS_SESSION_KEY, session.getAccessToken(), session);
    }

    public RedisSession findOne(String accessToken) {
        return (RedisSession) hashOperations.get(Constant.REDIS_SESSION_KEY, accessToken);
    }

    public void delete(final String accessToken) {
        hashOperations.delete(Constant.REDIS_SESSION_KEY, accessToken);
    }

}
