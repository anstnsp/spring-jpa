package com.audgml.demo.web.dto;

import com.audgml.demo.domain.posts.Posts;

import lombok.Builder;

public class PostsSaveRequestDto {
  private String title; 
  private String content; 
  private String author; 
  
  @Builder
  public PostsSaveRequestDto(String title, String content, 
    String author)  {
      this.title = title; 
      this.content = content; 
      this.author = author; 
    }

    public Posts toEntity() {
      return Posts.builder()
                  .title(title)
                  .content(content)
                  .author(author)
                  .build();
    }

}