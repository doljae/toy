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

        types.forEach(color -> {
            System.out.println(color);
            System.out.println(color.getClass());
        });

        return ResponseDto.builder().id(1).name("seokjae").age(31).build();
    }
}
