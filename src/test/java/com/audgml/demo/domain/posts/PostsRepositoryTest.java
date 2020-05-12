package com.audgml.demo.domain.posts;


import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

  @Autowired
  PostsRepository postsRepository; 

  @After
  public void cleanup() {
    postsRepository.deleteAll();
  }
  
  @Test
  public void 게시글저장_불러오기() {
    //given 
    String title = "테스트게시글";
    String content = "테스트본문"; 
    //.save()는 upsert 역할을 함. 
    postsRepository.save(Posts.builder()
                    .title(title)
                    .content(content)
                    .author("asd@anstnsp.com").build());
                    
    //when 
    List<Posts> postlist = postsRepository.findAll(); 

    //then 
    Posts posts = postlist.get(0);
    assertThat(posts.getTitle()).isEqualTo(title);
    assertThat(posts.getContent()).isEqualTo(content);
  }

  @Test
  public void BaseTimeEntity_등록() {
    //given 
    LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
    postsRepository.save(Posts.builder()
                    .title("title")
                    .content("content")
                    .author("author")
                    .build()); 
    //when 
    List<Posts> postsList = postsRepository.findAll(); 

    //then 
    Posts posts = postsList.get(0); 

    System.out.println(">> createDate = "+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());
    
    assertThat(posts.getCreatedDate()).isAfter(now); 
    assertThat(posts.getModifiedDate()).isAfter(now); 

  }

  @Test
  public void 전체조회() {
    //given 
    postsRepository.save(Posts.builder()
    .author("test1")
    .content("content1")
    .title("title1").build());

    postsRepository.save(Posts.builder()
    .author("test2")
    .content("content2")
    .title("title2").build());
    //when 
    List<Posts> list = postsRepository.findAllDesc();
    //then 
    assertThat(list.size()).isEqualTo(2); 
  }

}
