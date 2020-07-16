package com.audgml.demo.web;

import com.audgml.demo.domain.user.User;
import com.audgml.demo.domain.user.UserRepository;
import com.audgml.demo.exception.ResourceNotFoundException;
import com.audgml.demo.exception.UserExistException;
import com.audgml.demo.security.CurrentUser;
import com.audgml.demo.security.UserPrincipal;
import com.audgml.demo.service.user.UserService;
import com.audgml.demo.web.dto.user.LoginRequestDto;
import com.audgml.demo.web.dto.user.SignupRequestDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);
  private final UserRepository userRepository;
  private final UserService userService;

  @GetMapping("/user/me")
  @PreAuthorize("hasRole('USER')") // @PreAuthorize 먼지 찾아보기
  public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {

    return userRepository.findById(userPrincipal.getId())
        .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
  }

  // @PostMapping("/user/signup")
  // public Long signUp(@RequestBody SignupRequestDto signupRequestDto) throws UserExistException {
  //   logger.debug("### signUp start ###");

  //   if (userRepository.existsByEmail(signupRequestDto.getEmail())) {
  //     throw new UserExistException(signupRequestDto.getEmail() + "이 이미 존재합니다.");
  //   }

  //   return userService.createUser(signupRequestDto);

  // }

  // @PostMapping("/user/signin")
  // public String signIn(@RequestBody LoginRequestDto loginRequestDto) {

  //   return userService.signIn(loginRequestDto);

  // }
}