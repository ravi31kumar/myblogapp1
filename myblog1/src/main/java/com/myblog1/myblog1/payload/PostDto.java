package com.myblog1.myblog1.payload;


import lombok.Data;

@Data
public class PostDto {

    private long id;
    private String title;
    private String description;
    private String content;
}
