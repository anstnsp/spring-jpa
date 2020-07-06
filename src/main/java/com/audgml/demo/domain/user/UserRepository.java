package com.audgml.demo.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

//User의 CRUD를 책임질 UserRepository 
public interface UserRepository extends JpaRepository<User, Long> {

  // 소셜 로그인으로 반환되는 값 중 email을 통해 이미 생성된 사용자인지 처음 가입하는 사용자인지 판단하기 위한 메소드 .
  Optional<User> findByEmail(String email);

  Boolean existsByEmail(String email);

}