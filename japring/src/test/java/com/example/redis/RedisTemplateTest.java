package com.example.redis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import com.example.containers.AbstractTestContainers;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

@SpringBootTest
class RedisTemplateTest extends AbstractTestContainers {

    @Autowired
    private RedisTemplate<String, Long> redisLongTemplate;
    @Autowired
    private RedisTemplate<String, Integer> redisIntegerTemplate;

    @DisplayName("프로젝트 기본 설정, Value 타입을 Long으로 받을 때 ClassCastException이 발생한다")
    @Test
    void redisTemplate_value_type_is_Long() throws JsonProcessingException {
        final Long valueWrapper = 10L;
        final long valuePrimitive = 10L;

        final String wrapperKey = "Long";
        final String primitiveKey = "long";

        redisLongTemplate.opsForValue().set(wrapperKey, valueWrapper);
        redisLongTemplate.opsForValue().set(primitiveKey, valuePrimitive);

        try {
            final Long wrapperResult = redisLongTemplate.opsForValue().get(wrapperKey);
            final long primitiveResult = redisLongTemplate.opsForValue().get(primitiveKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @DisplayName("프로젝트 기본 설정, Value 타입을 Integer로 받을 때 ClassCastException이 발생한다")
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

    @DisplayName("설정 변경한 ObjectMapper를 사용한 Deserializer를 사용, Value 타입을 Long로 받을 때 "
                 + "oClassCastException이 발생하지 않는다")
    @Test
    void redisTemplate_value_type_is_Long_V1() throws JsonProcessingException {

        // ==========  Deserializer의 ObjectMapper 설정 변경 ==========================
        final GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer =
                new GenericJackson2JsonRedisSerializer(objectMapperAdjustedV1Version());
        redisLongTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        // =============================================================================

        final Long valueWrapper = 10L;
        final long valuePrimitive = 10L;

        final String wrapperKey = "Long";
        final String primitiveKey = "long";

        redisLongTemplate.opsForValue().set(wrapperKey, valueWrapper);
        redisLongTemplate.opsForValue().set(primitiveKey, valuePrimitive);

        final Long wrapperResult = redisLongTemplate.opsForValue().get(wrapperKey);
        final long primitiveResult = redisLongTemplate.opsForValue().get(primitiveKey);
    }

    @DisplayName("설정 변경한 ObjectMapper를 사용한 Deserializer를 사용, DefaultTyping도 같이 사용하면"
                 + "Value 타입을 Long로 받을 때 ClassCastException이 발생한다")
    @Test
    void redisTemplate_value_type_is_Long_V2() throws JsonProcessingException {

        // ==========  Deserializer의 ObjectMapper 설정 변경 ==========================
        final GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer =
                new GenericJackson2JsonRedisSerializer(objectMapperAdjustedV2Version());
        redisLongTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        // =============================================================================

        final Long valueWrapper = 10L;
        final long valuePrimitive = 10L;

        final String wrapperKey = "Long";
        final String primitiveKey = "long";

        redisLongTemplate.opsForValue().set(wrapperKey, valueWrapper);
        redisLongTemplate.opsForValue().set(primitiveKey, valuePrimitive);

        try {
            final Long wrapperResult = redisLongTemplate.opsForValue().get(wrapperKey);
            final long primitiveResult = redisLongTemplate.opsForValue().get(primitiveKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ObjectMapper objectMapperAdjustedV1Version() {
        final ObjectMapper mapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule(JsonCreator.Mode.DEFAULT))
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                // new option
                .configure(DeserializationFeature.USE_LONG_FOR_INTS, true);

        return mapper;
    }

    private static ObjectMapper objectMapperAdjustedV2Version() {
        final ObjectMapper mapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule(JsonCreator.Mode.DEFAULT))
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.USE_LONG_FOR_INTS, true);

        mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(),
                                     DefaultTyping.NON_FINAL,
                                     As.PROPERTY);

        return mapper;
    }
}
