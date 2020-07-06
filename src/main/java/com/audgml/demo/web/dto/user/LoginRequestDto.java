package com.audgml.demo.web.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.audgml.demo.domain.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequestDto {
  @NotBlank
  @Email
  private String email;

  @NotBlank
  private String password;

  @Builder
  public LoginRequestDto(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public User toEntity() {
    return User.builder().email(email).password(password).build();
  }

}