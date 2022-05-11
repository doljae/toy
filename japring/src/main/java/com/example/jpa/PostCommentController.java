package com.example.jpa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostCommentController {

    private final PostCommentService postCommentService;

    @GetMapping("/jpa/1")
    public String tes1() {

        postCommentService.saveComment();
        return "saveComment";
    }

    @GetMapping("/jpa/2")
    public String test2() {

        postCommentService.manuplate();
        return "manuplate";
    }

    @GetMapping("/jpa/3")
    public String test3() {

        postCommentService.getPostFromComment();
        return "getPostFromComment";
    }
}
