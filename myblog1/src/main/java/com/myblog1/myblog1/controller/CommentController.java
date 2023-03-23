package com.myblog1.myblog1.controller;

import com.myblog1.myblog1.payload.CommentDto;
import com.myblog1.myblog1.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/")
public class CommentController {
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    private CommentService commentService;

   //http://localhost:8080/api/posts/1/comments
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto>createCommnet(@PathVariable("postId") long postId, @RequestBody CommentDto commentDto){
        CommentDto dto = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);


    }
    //http://localhost:8080/api/posts/1/comments
    @GetMapping("/posts/{postId}/comments")
    List<CommentDto> getCommentsByPostId(@PathVariable("postId") long postId){
        return commentService.getCommentsByPostId(postId);

    }
    //http://localhost:8080/api/posts/1/comments/1
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("postId") long postId,@PathVariable("id") long id,
                                                    @RequestBody CommentDto commentDto){
        CommentDto dto = commentService.updateComment(postId, id, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




}
