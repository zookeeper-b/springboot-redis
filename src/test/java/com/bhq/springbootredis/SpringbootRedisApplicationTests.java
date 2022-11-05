package com.bhq.springbootredis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.UUID;

@SpringBootTest
class SpringbootRedisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        for (int i = 0; i < 5; i++) {
            String uuid = UUID.randomUUID().toString().toLowerCase();
            String[] result=uuid.split("-");
            for (String s:result){
                System.out.println(s);
            }
        }
    }

    @Test
    public void testRedis(){
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("age",41);
        Object age = ops.get("age");
        System.out.println(age);

    }
    @Test
    public void testHset(){
        HashOperations ops = redisTemplate.opsForHash();
        ops.put("info","a","aa");
        ops.put("user","name","bhq");
    }
    @Test
    public void testHget(){
        HashOperations ops = redisTemplate.opsForHash();
        Object o = ops.get("user", "name");
        Object o1 = ops.get("info", "a");
        System.out.println(o1);
        System.out.println(o);
    }



}
