package com.example.redis;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ObjectMapperTest {

    @DisplayName("project default setting")
    @Test
    void objectMapper_current_project_version() throws IOException {

        final ObjectMapper objectMapper = objectMapperProjectVersion();
        final Long longValue = 10L;
        final byte[] serialized = objectMapper.writeValueAsBytes(longValue);
        final Object deserialized = objectMapper.readValue(serialized, Object.class);

        log.info("Possible to cast Long?: {}", deserialized instanceof Long); // false
        log.info("Possible to cast Integer?: {}", deserialized instanceof Integer); // true
    }

    @DisplayName("enable USE_LONG_FOR_INTS option to ObjectMapper without DefaultTyping")
    @Test
    void objectMapper_adjusted_v1_version() throws IOException {
        final ObjectMapper objectMapper = objectMapperAdjustedV1Version();
        final Long longValue = 10L;
        final byte[] serialized = objectMapper.writeValueAsBytes(longValue);
        final Object deserialized = objectMapper.readValue(serialized, Object.class);

        log.info("Possible to cast Long?: {}", deserialized instanceof Long); // true
        log.info("Possible to cast Integer?: {}", deserialized instanceof Integer); // false
    }

    @DisplayName("enable USE_LONG_FOR_INTS option to ObjectMapper with DefaultTyping")
    @Test
    void objectMapper_adjusted_v2_version() throws IOException {
        final ObjectMapper objectMapper = objectMapperAdjustedV2Version();
        final Long longValue = 10L;
        final byte[] serialized = objectMapper.writeValueAsBytes(longValue);
        final Object deserialized = objectMapper.readValue(serialized, Object.class);

        log.info("Possible to cast Long?: {}", deserialized instanceof Long); // false
        log.info("Possible to cast Integer?: {}", deserialized instanceof Integer); // true
    }

    private static ObjectMapper objectMapperProjectVersion() {
        final ObjectMapper mapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule(JsonCreator.Mode.DEFAULT))
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(),
                                     DefaultTyping.NON_FINAL,
                                     As.PROPERTY);
        return mapper;
    }

    private static ObjectMapper objectMapperAdjustedV1Version() {
        final ObjectMapper mapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule(JsonCreator.Mode.DEFAULT))
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
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
