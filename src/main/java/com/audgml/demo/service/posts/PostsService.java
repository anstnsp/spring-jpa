package com.audgml.demo.service.posts;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.audgml.demo.domain.posts.Posts;
import com.audgml.demo.domain.posts.PostsRepository;
import com.audgml.demo.web.dto.PostsListReponseDto;
import com.audgml.demo.web.dto.PostsResponseDto;
import com.audgml.demo.web.dto.PostsSaveRequestDto;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {

  /**
   * 1.@Autowired. 2.setter 3.생성자 
   * 이 중 가장 권장하는 방식이 생성자로 주입받는 방식입니다(@Autowired 
    는 권장하지 않습니다). 즉 생성자로 Bean 객체를 받도록 하면 @Aurowired 
    와 동일한 효과를 볼 수 있다는 것입니다. 그러면 앞에서 생성자는 어디 
    있을까요? 
    바로 @RequiredArgsConstructor에서 해결해 줍니다. final이 선언된 모든 
    필드를 인자값으로 하는 생성자를 롬복의 @RequiredArgsConsrrucror가 
    대신 생성해 준 것입니다. 
    생성자를 직접 안 쓰고 롬복 어노테이션을 사용한 이유는 간단합니다. 
    해당 클래스의 의존성 관계가 변경될 때마다 생성자 코드를 계속해서 수 
    정하는 번거로움을 해결하기 위함입니다 
   */
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
    //.map(posts -> new PostListResponseDto(posts)) == .map(PostsListResponseDto::new)
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