package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.HappyPerson;
import com.example.domain.Color;
import com.example.dto.ResponseDto;
import com.example.feign.TestFeignClient;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TestController {

    @Autowired
    private TestFeignClient feignClient;

    @GetMapping("/test2")
    public ResponseEntity<Object> test2() {
        feignClient.test(List.of(Color.RED));
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/test")
    public ResponseDto test(@RequestParam("types") List<Color> types) {

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        types.forEach(color -> {
            System.out.println(color);
            System.out.println(color.getClass());
        });

        String collect = types.stream().map(Enum::name).collect(Collectors.joining(","));

        return ResponseDto.builder().build();
    }

    @GetMapping("/test/submodule")
    public ResponseDto testWithSubmoduleClass() {
        return ResponseDto.builder().body(new HappyPerson("teddy")).build();
    }

    @GetMapping("/test/unauthorized")
    public ResponseEntity<HappyPerson> testUnauthorized() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HappyPerson("teddy"));
    }

    @GetMapping("/test/{stringId}")
    public ResponseDto test(@PathVariable String stringId) {
        log.info("Called API: GET /test/{stringId}, {}", stringId);

        return ResponseDto.builder().build();
    }

    @GetMapping("/test/{longId}")
    public ResponseDto test(@PathVariable Long longId) {
        log.info("Called API: GET /test/{stringId}, {}", longId);

        return ResponseDto.builder().build();
    }
}
