package com.example.feign;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.Color;

import feign.Response;
import lombok.extern.slf4j.Slf4j;

// @Disabled
@Slf4j
@SpringBootTest
class TestFeignClientTest {

    @Autowired
    TestFeignClient feignClient;

    @Test
    void test() {
        final Response response = feignClient.test(List.of(Color.RED, Color.BLUE));
        assertNotEquals("http://localhost:1234/test?types=RED,BLUE", response.request().url());
    }

    @Test
    void test2() {
        final Response response1 = feignClient.test(List.of(Color.RED, Color.BLUE));
        final Response response2 = feignClient.testWithCollectionFormat(List.of(Color.RED, Color.BLUE));

        log.info("REQUEST URI : {}", response1.request().url());
        log.info("REQUEST URI with CollectionFormat : {}", response2.request().url());
    }
}