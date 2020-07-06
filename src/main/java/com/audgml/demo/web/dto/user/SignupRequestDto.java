package com.audgml.demo.web.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.audgml.demo.domain.user.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignupRequestDto {
  @NotBlank
  private String name;

  @NotBlank
  @Email
  private String email;

  @NotBlank
  private String password;

  @Builder
  public SignupRequestDto(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public User toEntity() {
    return User.builder().name(name).email(email).password(password).build();
  }
}