package com.example.feign;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

import com.example.domain.Color;

import feign.Response;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;

@Disabled
@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TestFeignClientTest {

    @Autowired
    TestFeignClient feignClient;

    @Autowired
    MockMvc mockMvc;

    @Test
    void test() {
        final Response response;
//        try {
        response = feignClient.test(List.of(Color.RED, Color.BLUE));
//        } catch (RetryableException e) {
//            log.error(e.toString());
//            log.error(e.getMessage());
//            log.error(e.getCause().toString());
//            log.error(e.getLocalizedMessage());
//            log.error(e.getClass().getName());
//            log.error(Arrays.toString(e.getSuppressed()));
//            log.error(Arrays.toString(Arrays.stream(e.getStackTrace()).toArray()));
//            e.request().headers().forEach((key, value) -> log.error(key + " / " + value));
//        }

//        assertNotEquals("http://localhost:1234/test?types=RED,BLUE", response.request().url());
    }

    @Test
    void test2() {
        final Response response1 = feignClient.test(List.of(Color.RED, Color.BLUE));
        final Response response2 = feignClient.testWithCollectionFormat(List.of(Color.RED, Color.BLUE));

        log.info("REQUEST URI : {}", response1.request().url());
        log.info("REQUEST URI with CollectionFormat : {}", response2.request().url());
    }

    public void test(RetryableException exception) {

    }
}