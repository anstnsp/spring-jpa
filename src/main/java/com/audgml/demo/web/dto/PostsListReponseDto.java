package com.audgml.demo.web.dto;

import java.time.LocalDateTime;

import com.audgml.demo.domain.posts.Posts;

import lombok.Getter;

@Getter
public class PostsListReponseDto {
  private Long id; 
  private String title; 
  private String content;
  private String author; 
  private LocalDateTime modifiedDate; 
  private LocalDateTime createdDate; 

  public PostsListReponseDto(Posts entity) {
    this.id = entity.getId();
    this.title = entity.getTitle(); 
    this.content = entity.getContent();
    this.author = entity.getAuthor();
    this.modifiedDate = entity.getModifiedDate();
    this.createdDate = entity.getCreatedDate();
  }
  
}