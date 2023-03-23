package com.myblog1.myblog1.payload;


import com.myblog1.myblog1.entity.Post;
import lombok.Data;

@Data
public class CommentDto {

    private long id;
    private String name;
    private String email;
    private String body;


}
