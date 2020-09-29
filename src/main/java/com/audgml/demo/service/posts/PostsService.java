package com.audgml.demo.service.posts;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.audgml.demo.domain.posts.Posts;
import com.audgml.demo.domain.posts.PostsRepository;

import com.audgml.demo.web.dto.request.posts.PostsSaveRequestDto;
import com.audgml.demo.web.dto.response.posts.PostsListReponseDto;
import com.audgml.demo.web.dto.response.posts.PostsResponseDto;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {

  private final PostsRepository postsRepository; 

  @Transactional
  public Long save(PostsSaveRequestDto requestDto) {
    return postsRepository.save(requestDto.toEntity()).getId(); 
  }

  @Transactional
  public Long update(Long id, PostsSaveRequestDto requestDto) {
    Posts posts = postsRepository.findById(id).orElseThrow(
      () -> new IllegalArgumentException("해당 사용자가 없습니다. id="+ id)); 

      posts.update(requestDto.getTitle(), requestDto.getContent());
      return id; 
    
  }

  public PostsResponseDto findById(Long id) {
    Posts entity = postsRepository.findById(id)
                  .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+ id));
    return new PostsResponseDto(entity);
  }

  @Transactional
  public List<PostsListReponseDto> findAllDesc() {
    return postsRepository.findAllDesc().stream()
                          .map(PostsListReponseDto::new)
                          .collect(Collectors.toList());
  }

  @Transactional
  public void delete(Long id) {
    Posts posts = postsRepository.findById(id)
                      .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+ id));
    postsRepository.delete(posts);
  }
  
}