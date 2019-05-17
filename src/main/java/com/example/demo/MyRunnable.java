package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
public class MyRunnable implements Runnable{
    private MsService service;
    private RedisTemplate<String,Object> redisTemplate;
    private String key;



    public MyRunnable() {
        this.redisTemplate=ApplicationContextProvider.getBean(RedisTemplate.class);
        this.service=ApplicationContextProvider.getBean(MsService.class);
        this.key="stock";
    }



    @Override
    public void run() {
        service.seckill(redisTemplate, key);
    }
}
