package com.audgml.demo.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.audgml.demo.config.auth.dto.AuthProvider;
import com.audgml.demo.domain.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
@Setter
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class User extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name; // 유저이름

  @Email
  @Column(nullable = false, unique = true)
  private String email;

  @JsonIgnore
  private String password;

  @Column
  private String picture;

  @Column(nullable = true)
  private Boolean emailVerified = false;

  @NotNull
  @Enumerated(EnumType.STRING)
  private AuthProvider provider;

  private String providerId;

  /**
   * @Enumerated(EnumType.STRING) - JPA로 디비에 저장할 때 Enum 값을 어떤 형태로 저장할지 결정. -
   * 기본적으로는 int로 된 숫자 저장. - 숫자로 저장되면 디비로 확인할 때 그 값이 무슨의미인지 잘 알수가 없어서 문자열로 저장함.
   */
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;

  @Builder
  public User(String name, String email, String password, String picture, AuthProvider provider, String providerId,
      Role role) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.picture = picture;
    this.provider = provider;
    this.providerId = providerId;
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