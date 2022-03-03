package com.example.feign;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.Color;

import feign.Response;

@Disabled
@SpringBootTest
class TestFeignClientTest {

    @Autowired
    TestFeignClient feignClient;

    @Test
    void test() {
        final Response response = feignClient.test(List.of(Color.RED, Color.BLUE));
        assertNotEquals("http://localhost:1234/test?types=RED,BLUE", response.request().url());
    }
}