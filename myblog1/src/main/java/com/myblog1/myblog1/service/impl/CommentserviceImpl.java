package com.myblog1.myblog1.service.impl;

import com.myblog1.myblog1.Myblog1Application;
import com.myblog1.myblog1.entity.Comment;
import com.myblog1.myblog1.entity.Post;
import com.myblog1.myblog1.exception.ResourceNotFound;
import com.myblog1.myblog1.payload.CommentDto;
import com.myblog1.myblog1.repository.CommentRepository;
import com.myblog1.myblog1.repository.PostRepository;
import com.myblog1.myblog1.service.CommentService;
import com.sun.corba.se.impl.protocol.RequestCanceledException;
import com.sun.deploy.security.BlockedException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.text.BadLocationException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentserviceImpl implements CommentService {


    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public CommentserviceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository= commentRepository;
    }


    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment =mapToEntity(commentDto);

        Post post = postRepository.findById(postId).orElseThrow
                (() -> new ResourceNotFound("Post", "Id", postId));
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);

        CommentDto dto =mapToDto(newComment);

        return dto;
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId ) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment-> mapToDto(comment)).collect(Collectors.toList());

    }

    @Override
    public CommentDto updateComment(long postId, long id, CommentDto commentDto) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("post", "Id", postId)
        );


        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("comment", "Id", id)
        );


         comment.setId(id);
         comment.setName(commentDto.getName());
         comment.setEmail(commentDto.getEmail());
         comment.setBody(commentDto.getBody());

        Comment newcomment = commentRepository.save(comment);

        return mapToDto(newcomment);
    }

    private CommentDto mapToDto(Comment newComment) {
        CommentDto commentDto=new CommentDto();
        commentDto.setId(newComment.getId());
        commentDto.setName(newComment.getName());
        commentDto.setEmail(newComment.getEmail());
        commentDto.setBody(newComment.getBody());
        return commentDto;

    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment= new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }


}
