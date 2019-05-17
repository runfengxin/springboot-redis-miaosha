package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: RedisTestController
 * @Auther: zhangyingqi
 * @Date: 2018/8/28 17:24
 * @Description:
 */
@Controller
@RequestMapping("/redis")
public class RedisTestController {

    @Autowired
    private RedisTemplate redisTemplate;


    ExecutorService executor = Executors.newFixedThreadPool(50);

    @RequestMapping(value="/miaosha")
    @ResponseBody
    public String miaosha() {
        for (int i = 0; i < 100; i++) {//设置1000个人来发起抢购
            executor.execute(new MyRunnable());
        }
        executor.shutdown();
        return "ok";
    }

    @RequestMapping(value="/setStock")
    @ResponseBody
    public String setStock() {
        redisTemplate.opsForValue().set("stock",100);
        return "ok";
    }

}
