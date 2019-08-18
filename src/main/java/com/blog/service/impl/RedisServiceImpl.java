package com.blog.service.impl;

import com.blog.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis擦破做Service的实现类
 */
@Service
public class RedisServiceImpl implements RedisService {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForSet().add(key, value);
    }

    @Override
    public Set get(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    public boolean expire(String key, long expire) {
        Boolean IsExpire = redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        return IsExpire;
    }

    @Override
    public void remove(String key, String value) {
        Objects.requireNonNull(redisTemplate.opsForSet().members(key)).remove(value);
    }

}
