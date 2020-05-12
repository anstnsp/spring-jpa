package com.audgml.demo.domain.user;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.audgml.demo.domain.BaseTimeEntity;



import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; 

  @Column(nullable = false)
  private String name; 

  @Column(nullable = false)
  private String email; 

  @Column 
  private String picture; 

  /**
   * @Enumerated(EnumType.STRING)
   * - JPA로 디비에 저장할 때 Enum 값을 어떤 형태로 저장할지 결정. 
   * - 기본적으로는 int로 된 숫자 저장. 
   * - 숫자로 저장되면 디비로 확인할 때 그 값이 무슨의미인지 잘 알수가 없어서 문자열로 저장함. 
   */
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role; 

  @Builder
  public User(String name, String email, String picture, Role role) {
    this.name = name; 
    this.email = email; 
    this.picture = picture;
    this.role = role;
  }

  public User update(String name, String picture) {
    this.name = name; 
    this.picture = picture; 
    
    return this; 
  }

  public String getRoleKey() {
    return this.role.getKey(); 
  }

}