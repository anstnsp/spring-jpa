package com.audgml.demo.web.dto.response.post;

import com.audgml.demo.domain.posts.Posts;

import lombok.Getter;

@Getter
public class PostsResponseDto {

  private Long id; 
  private String title; 
  private String content; 
  private String author; 
  
  /**
   * PostsResponseDto는 Entity의 필드 중 일부만 사용하므로 생성자로 Entity 
    를 받아 필드에 값을 넣습니다. 굳이 모든 필드를 가진 생성자가 필요하 
    진 않으므로 Dto는 Entity를 받아 처리합니다.
   * @param entity
   */
  public PostsResponseDto(Posts entity) {
    this.id = entity.getId(); 
    this.title = entity.getTitle();
    this.content = entity.getContent();
    this.author = entity.getAuthor();
  }

}