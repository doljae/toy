package com.example.jpa;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddPostCommentData implements ApplicationListener<ApplicationStartedEvent> {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        final Post post1 = new Post(1L, "title1");
        final Post post2 = new Post(2L, "title2");
        final Post post3 = new Post(3L, "title3");

        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
    }
}
