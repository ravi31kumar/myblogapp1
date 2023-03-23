package com.myblog1.myblog1.controller;


import com.myblog1.myblog1.payload.PostDto;
import com.myblog1.myblog1.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //http://localhost:8080/api/posts
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);


    }
    //http://localhost:8080/api/posts?pageNo=0&pageSize=5&sortBy=title
   @GetMapping
   public List<PostDto> getAllPosts(@RequestParam(value="pageNo",defaultValue = "0", required = false) int pageNo,
                                    @RequestParam(value="pageSize",defaultValue = "0",required=false) int pageSize,
                                    @RequestParam(value="sortBy", defaultValue = "id", required=false) String sortBy) {
       return postService.getAllposts(pageNo, pageSize, sortBy);

   }




   //http://localhost:8080/api/posts/1

  @GetMapping("/{id}")
    public ResponseEntity<PostDto> getpostById(@PathVariable("id") long id){
       PostDto dto =postService.getPostById(id);
       return ResponseEntity.ok(dto);

 }
    //http://localhost:8080/api/posts/1
   @PutMapping("/{id}")
   public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto, @PathVariable("id") long id){
        PostDto dto = postService.updatePost(postDto, id);
       return new ResponseEntity<>(dto,HttpStatus.OK);


   }



    //http://localhost:8080/api/posts/1
   @DeleteMapping("/{id}")
    public ResponseEntity<String>deletePost(@PathVariable(name="id") long id){
        postService.deletePostByid(id);
        return new ResponseEntity<>("Post deleted",HttpStatus.OK);
   }

}
