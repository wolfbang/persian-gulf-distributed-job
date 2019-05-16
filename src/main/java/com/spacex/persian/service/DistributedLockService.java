package com.spacex.persian.service;

import com.spacex.persian.util.DistributedLockLuaScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class DistributedLockService {

    private Logger logger = LoggerFactory.getLogger(DistributedLockService.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public boolean acquire(String key, Long limitMax, Long expiredTime) {
        logger.info(String.format("RateLimiterServiceImpl#acquire key:%s,limitMax:%s,expiredTime:%s", key, limitMax, expiredTime));
        List<String> keys = Arrays.asList(key);
        String rateLimiterLuaScript = DistributedLockLuaScript.getRateLimiterScript();
        RedisScript<Boolean> luaScript = new DefaultRedisScript<Boolean>(rateLimiterLuaScript, Boolean.class);
        boolean result = stringRedisTemplate.execute(luaScript, keys, limitMax.toString(), expiredTime.toString());
        return result;
    }
}
