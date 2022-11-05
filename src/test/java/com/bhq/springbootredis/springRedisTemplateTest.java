package com.bhq.springbootredis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @author bhq
 * @date 2022/10/21--14:58
 */

@SpringBootTest
public class springRedisTemplateTest {

    //操作单元是对象，但是redis客户端里面操作单元是字符串
    private RedisTemplate redisTemplate;

    //操作单元是字符串，要保证springboot整合redis操作的redis数据库中的数据和redis客户端操作的数据是一致的，就要用这个
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testGet(){
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set("k1","v1");
        String k1 = stringStringValueOperations.get("k1");
        System.out.println(k1);
    }


}
