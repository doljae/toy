package com.example;

import static java.util.function.Predicate.not;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.dto.ResponseDto;
import com.example.exception.CustomException;

import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class WebClientTest {

    @Autowired
    private WebClient webClient;


    @Test
    void test() {
        final Mono<ResponseEntity<ResponseDto>> response = webClient.get().uri("/test")
                                                                    .retrieve()
                                                                    .onStatus(unauthorized,
                                                                              clientResponse -> Mono.error(
                                                                                  new CustomException(
                                                                                      "unauthorized")))
                                                                    .onStatus(not(HttpStatus::is2xxSuccessful),
                                                                              clientResponse -> Mono.error(
                                                                                  new CustomException("not 2XX")))
                                                                    .toEntity(ResponseDto.class);

        System.out.println(response.block().getBody());


    }

    @Test
    void test2() {
        final Mono<ResponseEntity<HappyPerson>> response = webClient.get().uri("/test/unauthorized")
                                                                    .retrieve()
                                                                    .onStatus(unauthorized,
                                                                        clientResponse -> Mono.error(
                                                                            new CustomException(
                                                                                "unauthorized")))
                                                                    .onStatus(not(HttpStatus::is2xxSuccessful),
                                                                        clientResponse -> Mono.error(
                                                                            new CustomException("not 2XX")))
                                                                    .toEntity(HappyPerson.class);

        System.out.println(response.block().getBody());


    }

    private static Predicate<HttpStatus> unauthorized = httpStatus -> httpStatus == HttpStatus.UNAUTHORIZED;
}
