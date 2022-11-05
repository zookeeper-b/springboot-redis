package com.bhq.springbootredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bhq
 * @date 2022/10/15--16:13
 */
@RestController
@RequestMapping("/redisTest")
public class RedisTestController {

    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping
    public String testRedis() {
        //设置值到redis中
        redisTemplate.opsForValue().set("name", "lucy");
        //从redis中获取值
        Object name = redisTemplate.opsForValue().get("name");
        return (String) name;
    }

    @GetMapping("/testLock")
    public void testLock() {
        //获取所，查询num值
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", "111");
        //获取所成功，查询num值
        if (lock) {
            Object value = redisTemplate.opsForValue().get("num");
            //判断num为空返回return
            if (StringUtils.isEmpty(value)) {
                return;
            }
            //有值就转换成int
            int num = Integer.parseInt(value + "");
            //把redis的num加一
            redisTemplate.opsForValue().set("num", ++num);
            //释放锁，del
            redisTemplate.delete("lock");
        } else {
            //获取锁失败，每隔0.1秒在获取
            try {
                Thread.sleep(100);
                testLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
