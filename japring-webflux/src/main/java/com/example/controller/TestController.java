package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.HappyPerson;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final WebClient webClient;

    @GetMapping("/test/unauthorized")
    public ResponseEntity<HappyPerson> testUnauthorized() {
        HappyPerson block = webClient.get().uri("/test").retrieve().bodyToMono(HappyPerson.class).block();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HappyPerson("teddy"));
    }

    @GetMapping("/test")
    public ResponseEntity<HappyPerson> testWebClient() {
        return ResponseEntity.status(HttpStatus.OK).body(new HappyPerson("teddy"));
    }
}
