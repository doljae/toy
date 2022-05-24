package com.example;

import static java.util.function.Predicate.not;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.exception.CustomException;

import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class WebClientTest {

    @Autowired
    private WebClient webClient;
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void healthCheck() {
        webTestClient.get().uri("/test/unauthorized")
                     .exchange().expectStatus().isUnauthorized();
//                                                               .retrieve()
//                                                           .onStatus(unauthorized,
//                                                                     clientResponse -> Mono.error(
//                                                                         new CustomException(
//                                                                             "unauthorized")))
//                                                           .onStatus(
//                                                               not(HttpStatus::is2xxSuccessful),
//                                                               clientResponse -> Mono.error(
//                                                                   new CustomException(
//                                                                       "not 2XX")))
//                                                               .toEntity(ResponseDto.class).block());

    }

    @Test
    void testUsingWebClient() throws InterruptedException {
        Mono<ResponseEntity<HappyPerson>> mono = webClient.get().uri(
                                                              "localhost:1234/test/unauthorized").retrieve()
                                                          .onStatus(unauthorized,
                                                                    clientResponse -> Mono.error(
                                                                        new CustomException(
                                                                            "this is expected error 1")))
                                                          .onStatus(unauthorized,
                                                                    clientResponse -> Mono.error(
                                                                        new CustomException(
                                                                            "this is expected error 2")))
                                                          .onStatus(not(HttpStatus::is2xxSuccessful),
                                                                    clientResponse -> Mono.error(
                                                                        new CustomException(
                                                                            "this is expected error 3")))
                                                          .toEntity(
                                                              HappyPerson.class);

//        mono.doOnError(CustomException.class, exception -> {
//            System.out.println("works!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        });
//        mono.doOnSuccess(entity -> {
//            System.out.println(entity.getBody().getName());
//        });
        Thread.sleep(3000L);
        System.out.println(mono.block().getBody().getName());
    }

    private static Predicate<HttpStatus> unauthorized = httpStatus -> httpStatus == HttpStatus.UNAUTHORIZED;
}
