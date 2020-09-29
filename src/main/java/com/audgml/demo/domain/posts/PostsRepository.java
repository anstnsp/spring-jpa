package com.audgml.demo.domain.posts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * JPA에서 Repository는 보통 mybatis에서 Dao라고 불리는 디비접근자임 . 
 * Repository는 interfacce로 생성 후 <Entity 클래스, pk타입> 을 상속하면
 * 기본 CRUD 메소드가 자동으로 생성댐. 
 * 
 * ==>주의할 점은 Entity클래스와 기본 Entity Repository는 함께 위치해야함. 
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

  List<Posts> findAllDesc(); 
}