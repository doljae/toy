package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Cat;
import com.example.dto.RequestDto;
import com.example.service.CatService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(
    name = "Cat Controller V1",
    description = "API for Cat"
)
@RestController
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;

    @Operation(
        summary = "Return all cats",
        description = """
            | Response Code | Status         |
            | ------------- | -------------  |
            | 1234          | DB error       |
            | 5678          | Some error     |
            | 9999          | Maintenance    |
            """
    )
    @GetMapping("/cat/all")
    public ResponseEntity<List<Cat>> getCatList() {
        return ResponseEntity.ok(catService.getCatList());
    }

    @Operation(
        description = "Find person by e-mail",
        responses = {
            @ApiResponse(responseCode = "404", description = "There's no Cat in DB")
        })
    @GetMapping("/cat/all/annotation")
    public ResponseEntity<List<Cat>> getCatListWithAnnotation() {
        return ResponseEntity.ok(catService.getCatList());
    }

    @GetMapping("/cat/{id}")
    public ResponseEntity<Cat> getCatList(@PathVariable("id") int id) {
        return ResponseEntity.ok(catService.getCat(id));
    }

    @PostMapping("/cat")
    public ResponseEntity getCatList(@RequestBody RequestDto requestDto) {
        return ResponseEntity.ok().build();
    }
}
