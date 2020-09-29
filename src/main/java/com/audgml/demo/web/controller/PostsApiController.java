package com.audgml.demo.web.controller;

import java.util.List;

import com.audgml.demo.exception.BadRequestException;
import com.audgml.demo.service.posts.PostsService;

import com.audgml.demo.web.dto.request.posts.PostsSaveRequestDto;
import com.audgml.demo.web.dto.response.posts.PostsListReponseDto;
import com.audgml.demo.web.dto.response.posts.PostsResponseDto;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
public class PostsApiController {

  private final PostsService postsService; 

  @PostMapping("/api/v1/posts")
  public Long save(@RequestBody PostsSaveRequestDto requestDto) {
    System.out.println("#### postsave ####");
    System.out.println("dto:"+requestDto);
    System.out.println(requestDto.getAuthor());
    if(requestDto.getAuthor() == "") {
      throw new BadRequestException("작성자가 없음.(로그인 되어있지 않음.");
    }

    return postsService.save(requestDto); 
  }

  @PutMapping("/api/v1/posts/{id}")
  public Long update(@PathVariable Long id, @RequestBody PostsSaveRequestDto requestDto) {
    return postsService.update(id, requestDto); 
  }

  @GetMapping("/api/v1/posts/{id}")
  public PostsResponseDto findById(@PathVariable Long id) {
    return postsService.findById(id);
  }
  
  @GetMapping("/api/v1/posts")
  public List<PostsListReponseDto> findAll() {
    return postsService.findAllDesc();
  }
  @DeleteMapping("/api/v1/posts/{id}")
  public Long delete(@PathVariable Long id) {
    postsService.delete(id);
    return id; 
  }
  
  
}