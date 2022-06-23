package com.example.jpa;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    void checkQuery() {
        System.out.println("=== check query ===");
        // findById(id) -> id가 null이면 예외가 발생한다. .or(), .orElse() 등의 메서드는 예외를 캐치하진 못한다
        // Using Optional to hide a null-check - is an Antipattern,
        // https://stackoverflow.com/questions/72626469/optional-orelse-cannot-return-alternative-value-when-an-exception-occurs
        // final Optional<Post> byId1 = postRepository.findById(null);
        // final Post byId2 = postRepository.findById(null).orElse(null);
        System.out.println("=== check query ===");
    }
}
