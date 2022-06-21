//package com.example.redis;
//
//import java.time.OffsetDateTime;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
//
//@SpringBootTest
//class RedisRecordTest {
//
//    @Autowired
//    private RedisTemplate<String, RequestRecord> redisRecordTemplate;
//    @Autowired
//    private RedisTemplate<String, RequestRecord2> redisRecordTemplate2;
//    @Autowired
//    private RedisTemplate<String, RequestDto> redisDtoTemplate;
//    @Autowired
//    private RedisTemplate<String, RequestDto2> redisDtoTemplate2;
//    @Autowired
//    private RedisTemplate<String, String> redisStringTemplate;
//    @Autowired
//    private RedisTemplate<String, Object> redisObjectTemplate;
//    @Autowired
//    private RedisTemplate<String, ClassicRequestWrapper> redisWrapperTemplate;
//    @Autowired
//    private RedisTemplate<String, RequestFinalDto> redisFinalDtoTemplate;
//    @Autowired
//    private ObjectMapper mapper;
//
//    @DisplayName("record -> String -> set() -> get() -> String -> record -> O")
//    @Test
//    void test() throws JsonProcessingException {
//        final RequestRecord record = new RequestRecord(1L, "doljae", OffsetDateTime.now());
//        final String serialized = mapper.writeValueAsString(record);
//        System.out.println(serialized);
//        redisStringTemplate.opsForValue().set("key", serialized);
//
//        final String fromRedis = redisStringTemplate.opsForValue().get("key");
//        System.out.println(fromRedis);
//        final RequestRecord deserialized = mapper.readValue(fromRedis, RequestRecord.class);
//        System.out.println(deserialized);
//    }
//
//    @DisplayName("Class -> set() -> get() -> Class -> O")
//    @Test
//    void test2() {
//        final RequestDto dto = new RequestDto(1L, "doljae", OffsetDateTime.now());
//        redisDtoTemplate.opsForValue().set("key", dto);
//        /*
//         redis-cli
//         get key
//         -> "{\"@class\":\"com.example.redis.RequestDto\",\"id\":1,\"name\":\"doljae\",\"createdA\":\"2022-06-08T21:39:01.166705+09:00\"}"
//         */
//
//        final RequestDto fromRedis = redisDtoTemplate.opsForValue().get("key");
//        System.out.println(fromRedis);
//    }
//
//    @DisplayName("record -> set() -> get() -> record -> Xw")
//    @Test
//    void test3() {
//        final RequestRecord record = new RequestRecord(1L, "seokjae", OffsetDateTime.now());
//        redisRecordTemplate.opsForValue().set("key", record);
//        /*
//         redis-cli
//         get key
//         -> "{\"id\":1,\"name\":\"doljae\",\"createdAt\":\"2022-06-08T21:40:36.057138+09:00\"}"
//         */
//
//        // Caused by: com.fasterxml.jackson.databind.exc.InvalidTypeIdException: Could not resolve subtype of [simple type, class java.lang.Object]: missing type id property '@class'
//        // at [Source: (byte[])"{"id":1,"name":"doljae","createdAt":"2022-06-08T21:40:36.057138+09:00"}"; line: 1, column: 72]
//        final RequestRecord deserialized = redisRecordTemplate.opsForValue().get("key");
//    }
//
//    @Test
//    void test4() throws JsonProcessingException {
//
//        final RequestDto dto = new RequestDto(1L, "doljae", OffsetDateTime.now());
//        final RequestRecord record = new RequestRecord(1L, "seokjae", OffsetDateTime.now());
//
//        final byte[] dtoBytes = mapper.writeValueAsBytes(dto);
//        final byte[] recordBytes = mapper.writeValueAsBytes(record);
//
//        redisObjectTemplate.opsForValue().set("key1", dtoBytes);
//        redisObjectTemplate.opsForValue().set("key2", recordBytes);
//
//        final String dtoBytes2 = mapper.writerFor(RequestDto.class).writeValueAsString(dto);
//        final String recordBytes2 = mapper.writerFor(RequestRecord.class).writeValueAsString(record);
//
//        redisObjectTemplate.opsForValue().set("key3", dtoBytes2);
//        redisObjectTemplate.opsForValue().set("key4", recordBytes2);
//
//        final RequestRecord record1 = redisRecordTemplate.opsForValue().get("key4");
//        System.out.println(record1);
//
//    }
//
//    @Test
//    void test5() {
//        final RequestDto dto = new RequestDto(1L, "doljae", OffsetDateTime.now());
//        final ClassicRequestWrapper wrapper = new ClassicRequestWrapper(dto);
//
//        redisWrapperTemplate.opsForValue().set("key", wrapper);
//
//        final ClassicRequestWrapper classicRequestWrapper = redisWrapperTemplate.opsForValue().get("key");
//
//    }
//
//    @Test
//    void test6() throws JsonProcessingException {
//        final RequestDto dto = new RequestDto(1L, "doljae", OffsetDateTime.now());
//        final ClassicRequestWrapper wrapper = new ClassicRequestWrapper(dto);
//
//        redisWrapperTemplate.opsForValue().set("key", wrapper);
//
//        final String key = redisStringTemplate.opsForValue().get("key");
//        final ClassicRequestWrapper classicRequestWrapper = mapper.readValue(key, ClassicRequestWrapper.class);
//        System.out.println(classicRequestWrapper);
//        final Object wrapped = classicRequestWrapper.getWrapped();
//        if (wrapped instanceof RequestDto result) {
//            System.out.println("class");
//            System.out.println(result);
//        } else if (wrapped instanceof RequestRecord result) {
//            System.out.println("record");
//            System.out.println(result);
//        } else {
//            System.out.println("error");
//        }
//    }
//
//    @Test
//    void test7() throws JsonProcessingException {
//        final RequestDto2 dto = new RequestDto2(1L, "doljae");
//        final ClassicRequestWrapper wrapper = new ClassicRequestWrapper(dto);
//
//        redisWrapperTemplate.opsForValue().set("key", wrapper);
//
//        final ClassicRequestWrapper classicRequestWrapper = redisWrapperTemplate.opsForValue().get("key");
//        System.out.println(classicRequestWrapper);
//        assert classicRequestWrapper != null;
//        final Object wrapped = classicRequestWrapper.getWrapped();
//        if (wrapped instanceof RequestDto2 result) {
//            System.out.println("class");
//            System.out.println(result);
//        } else if (wrapped instanceof RequestRecord2 result) {
//            System.out.println("record");
//            System.out.println(result);
//        } else {
//            System.out.println("error");
//        }
//    }
//
//    @Test
//    void test8() throws JsonProcessingException {
//        final RequestRecord2 dto = new RequestRecord2(1L, "doljae");
//        final ClassicRequestWrapper wrapper = new ClassicRequestWrapper(dto);
//
//        redisWrapperTemplate.opsForValue().set("key", wrapper);
//
//        final ClassicRequestWrapper classicRequestWrapper = redisWrapperTemplate.opsForValue().get("key");
//        System.out.println(classicRequestWrapper);
//        assert classicRequestWrapper != null;
//        final Object wrapped = classicRequestWrapper.getWrapped();
//        if (wrapped instanceof RequestDto2 result) {
//            System.out.println("class");
//            System.out.println(result);
//        } else if (wrapped instanceof RequestRecord2 result) {
//            System.out.println("record");
//            System.out.println(result);
//        } else {
//            System.out.println("error");
//        }
//    }
//
//    @Test
//    void test9() throws JsonProcessingException {
//        final RequestFinalDto dto = RequestFinalDto.builder().id(1L)
//                                                   .name("seokjae")
//                                                   .createdAt(OffsetDateTime.now()).build();
////        final RequestRecord2 record = new RequestRecord2(1L, "doljae");
//
//        redisFinalDtoTemplate.opsForValue().set("key1", dto);
////        redisRecordTemplate2.opsForValue().set("key2", record);
//
//        final RequestFinalDto key1 = redisFinalDtoTemplate.opsForValue().get("key1");
////        final RequestRecord2 key2 = redisRecordTemplate2.opsForValue().get("key2");
//
//        System.out.println(key1);
////        System.out.println(key2);
//    }
//
//    @Test
//    void test10() throws JsonProcessingException {
//        final RequestRecord2 record = new RequestRecord2(1L, "doljae");
//
//        redisRecordTemplate2.opsForValue().set("key2", record);
//
//        final RequestRecord2 key2 = redisRecordTemplate2.opsForValue().get("key2");
//
//        System.out.println(key2);
//    }
//
//    @Test
//    void test11() throws JsonProcessingException {
//        final RequestRecord targetClass = new RequestRecord(1L, "doljae", OffsetDateTime.now());
//        final RequestWrapper wrapperClass = new RequestWrapper(targetClass);
//
//        final ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new ParameterNamesModule(JsonCreator.Mode.DEFAULT))
//              .registerModule(new Jdk8Module())
//              .registerModule(new JavaTimeModule())
//              .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
//              .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//              .configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
//
//        final String serialized = mapper.writeValueAsString(wrapperClass);
//        System.out.println(serialized);
//
//        final RequestWrapper requestWrapper = mapper.readValue(serialized, RequestWrapper.class);
//        final RequestRecord wrapped = requestWrapper.getWrapped();
//        System.out.println(wrapped);
//    }
//}
