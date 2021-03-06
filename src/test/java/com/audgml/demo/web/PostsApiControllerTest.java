package com.audgml.demo.web;



import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.audgml.demo.domain.posts.Posts;
import com.audgml.demo.domain.posts.PostsRepository;
import com.audgml.demo.web.dto.request.posts.PostsSaveRequestDto;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @WebMvcTest의 경우 JPA기능이 작동하지 않기 때문에 사용안함. 
 * @WebMvcTest는 @Controller와 @ControllerAdvice등 외부연동과 관련된 부분만 활성화 하니까 
 * 지금 같이 JPA기능까지 한번에 테스트할때는 @SpringBootTest와 TestRestTemplate를 사용하면 된다 . 
 */
@RunWith(SpringRunner.class) 
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

  @LocalServerPort
  private int port; 

  @Autowired
  private TestRestTemplate restTemplate;


  @Autowired
  private PostsRepository postsRepository;

  @After
  public void tearDown() throws Exception {
    postsRepository.deleteAll();
  }

  @Test
  public void Posts_둥록됨() throws Exception {
    //given 
    String title = "title"; 
    String content = "content"; 
    PostsSaveRequestDto reqDto = PostsSaveRequestDto.builder()
                                                    .title(title)
                                                    .content(content)
                                                    .author("author").build();
    String url = "http://localhost:"+port+"/api/v1/posts";

    //when 
    ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, reqDto, Long.class); 

    //then 
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isGreaterThan(0L);

    List<Posts> all = postsRepository.findAll(); 

    assertThat(all.get(0).getTitle()).isEqualTo(title); 
    assertThat(all.get(0).getContent()).isEqualTo(content); 
  }

  @Test
  public void Posts_수정된다() throws Exception {
    //given 
    Posts savedPosts =postsRepository.save(Posts.builder()
                      .title("title")
                      .content("content")
                      .author("author").build());
    
    Long updateId = savedPosts.getId();
    String expectedTitle = "title2";
    String expectedContent = "content2"; 

    PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                                        .title(expectedTitle)
                                        .content(expectedContent)
                                        .build(); 
    String url = "http://localhost:" + port + "/api/v1/posts/" + updateId; 
    HttpEntity<PostsSaveRequestDto> requestEntity = new HttpEntity<>(requestDto);

    //when 
    ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

    //then 
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK); 
    assertThat (responseEntity.getBody()).isGreaterThan(0L);
    List<Posts> all = postsRepository.findAll(); 
    assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle); 
    assertThat(all.get(0).getContent()).isEqualTo(expectedContent); 
  }
}