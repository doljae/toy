package com.example.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest
class RedisTemplateTest {

    @Autowired
    private RedisTemplate<String, Long> redisLongTemplate;
    @Autowired
    private RedisTemplate<String, Integer> redisIntegerTemplate;

    @Test
    void redisTemplate_value_type_is_Long() throws JsonProcessingException {
        final Long valueWrapper = 10L;
        final long valuePrimitive = 10L;

        final String wrapperKey = "Long";
        final String primitiveKey = "long";

        redisLongTemplate.opsForValue().set(wrapperKey, valueWrapper);
        redisLongTemplate.opsForValue().set(primitiveKey, valuePrimitive);

        final Long wrapperResult = redisLongTemplate.opsForValue().get(wrapperKey);
        final long primitiveResult = redisLongTemplate.opsForValue().get(primitiveKey);
    }

    @Test
    void redisTemplate_value_type_is_Integer() throws JsonProcessingException {
        final Integer valueWrapper = 10;
        final int valuePrimitive = 10;

        final String wrapperKey = "Integer";
        final String primitiveKey = "int";

        redisIntegerTemplate.opsForValue().set(wrapperKey, valueWrapper);
        redisIntegerTemplate.opsForValue().set(primitiveKey, valuePrimitive);

        final Integer wrapperResult = redisIntegerTemplate.opsForValue().get(wrapperKey);
        final int primitiveResult = redisIntegerTemplate.opsForValue().get(primitiveKey);
    }
}
