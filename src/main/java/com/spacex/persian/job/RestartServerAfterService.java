package com.spacex.persian.job;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RestartServerAfterService implements ApplicationRunner {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        String key = "";
        stringRedisTemplate.delete(key);
    }
}
