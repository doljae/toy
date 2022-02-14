package com.example.feign;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.Color;
import com.example.dto.ResponseDto;

@SpringBootTest
class TestFeignClientTest {

    @Autowired
    TestFeignClient feignClient;

    @Test
    void test() {
        ResponseDto test = feignClient.test(List.of(Color.RED, Color.BLUE));
        System.out.println(test);
    }
}