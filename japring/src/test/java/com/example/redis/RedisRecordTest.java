package com.example.redis;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class RedisRecordTest {

    @Autowired
    private RedisTemplate<String, RequestRecord> redisRecordTemplate;
    @Autowired
    private RedisTemplate<String, RequestDto> redisDtoTemplate;
    @Autowired
    private RedisTemplate<String, String> redisStringTemplate;
    @Autowired
    private RedisTemplate<String, Object> redisObjectTemplate;
    @Autowired
    private ObjectMapper mapper;

    @DisplayName("record -> String -> set() -> get() -> String -> record -> O")
    @Test
    void test() throws JsonProcessingException {
        final RequestRecord record = new RequestRecord(1L, "doljae", OffsetDateTime.now());
        final String serialized = mapper.writeValueAsString(record);
        System.out.println(serialized);
        redisStringTemplate.opsForValue().set("key", serialized);

        final String fromRedis = redisStringTemplate.opsForValue().get("key");
        System.out.println(fromRedis);
        final RequestRecord deserialized = mapper.readValue(fromRedis, RequestRecord.class);
        System.out.println(deserialized);
    }

    @DisplayName("Class -> set() -> get() -> Class -> O")
    @Test
    void test2() {
        final RequestDto dto = new RequestDto(1L, "doljae", OffsetDateTime.now());
        redisDtoTemplate.opsForValue().set("key", dto);
        /*
         redis-cli
         get key
         -> "{\"@class\":\"com.example.redis.RequestDto\",\"id\":1,\"name\":\"doljae\",\"createdA\":\"2022-06-08T21:39:01.166705+09:00\"}"
         */

        final RequestDto fromRedis = redisDtoTemplate.opsForValue().get("key");
        System.out.println(fromRedis);
    }

    @DisplayName("record -> set() -> get() -> record -> X")
    @Test
    void test3() {
        final RequestRecord record = new RequestRecord(1L, "seokjae", OffsetDateTime.now());
        redisRecordTemplate.opsForValue().set("key", record);
        /*
         redis-cli
         get key
         -> "{\"id\":1,\"name\":\"doljae\",\"createdAt\":\"2022-06-08T21:40:36.057138+09:00\"}"
         */

        // Caused by: com.fasterxml.jackson.databind.exc.InvalidTypeIdException: Could not resolve subtype of [simple type, class java.lang.Object]: missing type id property '@class'
        // at [Source: (byte[])"{"id":1,"name":"doljae","createdAt":"2022-06-08T21:40:36.057138+09:00"}"; line: 1, column: 72]
        final RequestRecord deserialized = redisRecordTemplate.opsForValue().get("key");
    }
}
