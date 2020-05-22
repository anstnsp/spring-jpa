package com.audgml.demo.web;

import com.audgml.demo.service.posts.PostsService;
import com.audgml.demo.web.dto.PostsResponseDto;
import com.audgml.demo.web.dto.PostsSaveRequestDto;

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
public class PostsApiController {

  private final PostsService postsService; 

  @PostMapping("/api/v1/posts")
  public Long save(@RequestBody PostsSaveRequestDto requestDto) {
    System.out.println("#### postsave ####");
    System.out.println("dto:"+requestDto);
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
  
  @DeleteMapping("/api/v1/posts/{id}")
  public Long delete(@PathVariable Long id) {
    postsService.delete(id);
    return id; 
  }
  
  
}