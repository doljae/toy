package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HappyPerson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TestController {

    @GetMapping("/test/unauthorized")
    public ResponseEntity<HappyPerson> testUnauthorized() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HappyPerson("teddy"));
    }
}
