package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Dog;
import com.example.service.DogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DogController {

    private final DogService dogService;

    @GetMapping("/dog/all")
    public ResponseEntity<List<Dog>> getCatList() {
        return ResponseEntity.ok(dogService.getDogList());
    }

    @GetMapping("/dog/{id}")
    public ResponseEntity<Dog> getDogList(@PathVariable("id") int id) {
        return ResponseEntity.ok(dogService.getDog(id));
    }
}
