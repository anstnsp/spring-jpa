package com.audgml.demo.web;

import com.audgml.demo.domain.user.User;
import com.audgml.demo.domain.user.UserRepository;
import com.audgml.demo.exception.ResourceNotFoundException;
import com.audgml.demo.security.CurrentUser;
import com.audgml.demo.security.UserPrincipal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

  private UserRepository userRepository;

  @GetMapping("/user/me")
  @PreAuthorize("hasRole('USER')") // @PreAuthorize 먼지 찾아보기
  public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
    return userRepository.findById(userPrincipal.getId())
        .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
  }

}