package com.example.jpa;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostCommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public void saveComment() {

        final Post post = postRepository.findById(1L).get();
        final Comment comment = new Comment(1L, post, "comment content");

        commentRepository.save(comment);
    }

    @Transactional
    public void manuplate() {
        final Post post = postRepository.findById(1L).get();
        post.setTitle("different title");
    }

    public void getPostFromComment() {
        final Comment comment = commentRepository.findById(1L).get();
        final Post postFromComment = comment.getPost();
        System.out.println("post from comment, title: " + postFromComment.getTitle());
    }

    public void doAllInSameTime(){

    }

}
