package com.audgml.demo.web;

import com.audgml.demo.domain.user.User;
import com.audgml.demo.domain.user.UserRepository;
import com.audgml.demo.exception.ResourceNotFoundException;
import com.audgml.demo.security.CurrentUser;
import com.audgml.demo.security.UserPrincipal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);
  private final UserRepository userRepository;

  @GetMapping("/user/me")
  @PreAuthorize("hasRole('USER')") // @PreAuthorize 먼지 찾아보기
  public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {

    logger.info("### getCurrentUser ###");
    logger.info(userPrincipal.getEmail());
    logger.info(userPrincipal.getId().toString());
    logger.info(userPrincipal.getName());

    return userRepository.findById(userPrincipal.getId())
        .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
  }

}