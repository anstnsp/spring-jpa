package com.audgml.demo.service.user;

import com.audgml.demo.config.auth.JwtTokenProvider;
import com.audgml.demo.config.auth.dto.AuthProvider;
import com.audgml.demo.domain.user.Role;
import com.audgml.demo.domain.user.User;
import com.audgml.demo.domain.user.UserRepository;
import com.audgml.demo.web.dto.user.LoginRequestDto;
import com.audgml.demo.web.dto.user.SignupRequestDto;
import java.util.Collections;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

  // private final UserRepository userRepository;
  // private final PasswordEncoder passwordEncoder;
  // private final JwtTokenProvider jwtTokenProvider;

  // public Long createUser(SignupRequestDto signupRequestDto) {
  //   // 1.비번 암호화해서 유저 세이브해야함.

  //   return userRepository.save(User.builder().name(signupRequestDto.getName())
  //       .email(signupRequestDto.getEmail())
  //       .password(passwordEncoder.encode(signupRequestDto.getPassword()))
  //       .provider(AuthProvider.local)
  //       .role(Role.USER).build()).getId();
  // }

  // public String signIn(LoginRequestDto loginRequestDto) {
  //   User loginUser = userRepository.findByEmail(loginRequestDto.getEmail())
  //       .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));

  //   if (!passwordEncoder.matches(loginRequestDto.getPassword(), loginUser.getPassword())) {
  //     throw new IllegalArgumentException("잘못된 비밀번호입니다.");
  //   }

  //   return jwtTokenProvider.createToken(loginUser.getEmail(), loginUser.getRole());
  // }

}