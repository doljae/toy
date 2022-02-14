package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Color;
import com.example.dto.ResponseDto;

@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseDto test(@RequestParam("types") List<Color> types) {
        return ResponseDto.builder().build();
    }
}
