package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Cat;
import com.example.service.CatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;

    @GetMapping("/cat/all")
    public ResponseEntity<List<Cat>> getCatList() {
        return ResponseEntity.ok(catService.getCatList());
    }

    @GetMapping("/cat/{id}")
    public ResponseEntity<Cat> getCatList(@PathVariable("id") int id) {
        return ResponseEntity.ok(catService.getCat(id));
    }
}
