package com.example.redis;

import java.time.OffsetDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

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

@ExtendWith(MockitoExtension.class)
class MapperTest {

    @Test
    void test() throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule(JsonCreator.Mode.DEFAULT))
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(),
                                     DefaultTyping.NON_FINAL,
                                     As.PROPERTY);

        final RequestDto dto = new RequestDto(1L, "doljae", OffsetDateTime.now());
        final RequestRecord record = new RequestRecord(1L, "seokjae", OffsetDateTime.now());

        final byte[] dtoBytes = mapper.writeValueAsBytes(dto);
        final byte[] recordBytes = mapper.writeValueAsBytes(record);

        System.out.println(Arrays.toString(dtoBytes));
        System.out.println(Arrays.toString(recordBytes));
    }

}
