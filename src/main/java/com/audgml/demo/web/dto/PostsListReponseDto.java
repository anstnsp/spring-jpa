package com.audgml.demo.web.dto;

import java.time.LocalDateTime;

import com.audgml.demo.domain.posts.Posts;

import lombok.Getter;

@Getter
public class PostsListReponseDto {
  private Long id; 
  private String title; 
  private String author; 
  private LocalDateTime modifiedDate; 

  public PostsListReponseDto(Posts entity) {
    this.id = entity.getId();
    this.title = entity.getTitle(); 
    this.author = entity.getAuthor();
    this.modifiedDate = entity.getModifiedDate();
  }
}