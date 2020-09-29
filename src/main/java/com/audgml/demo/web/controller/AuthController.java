package com.audgml.demo.web.controller;

import javax.validation.Valid;

import com.audgml.demo.config.auth.dto.AuthProvider;
import com.audgml.demo.domain.user.Role;
import com.audgml.demo.domain.user.User;
import com.audgml.demo.domain.user.UserRepository;
import com.audgml.demo.exception.BadRequestException;
import com.audgml.demo.security.TokenProvider;
import com.audgml.demo.web.dto.request.user.LoginRequestDto;
import com.audgml.demo.web.dto.request.user.SignupRequestDto;
import com.audgml.demo.web.dto.response.ApiResponse;
import com.audgml.demo.web.dto.response.AuthResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final TokenProvider tokenProvider;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {

    
    User loginUser = userRepository.findByEmail(loginRequestDto.getEmail())
    .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));

    if (!passwordEncoder.matches(loginRequestDto.getPassword(), loginUser.getPassword())) {
      throw new IllegalArgumentException("잘못된 비밀번호입니다.");
    }

    Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(),
                                    loginRequestDto.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = tokenProvider.createToken(authentication);
    return ResponseEntity.ok(new AuthResponse(token));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestDto signUpRequestDto) {
    if (userRepository.existsByEmail(signUpRequestDto.getEmail())) {
            throw new BadRequestException("해당 이메일이 이미 존재합니다.");
    }

    // Creating user's account
    User user = new User();
    user.setName(signUpRequestDto.getName());
    user.setEmail(signUpRequestDto.getEmail());
    user.setPassword(signUpRequestDto.getPassword());
    user.setProvider(AuthProvider.local);
    user.setRole(Role.USER);
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    userRepository.save(user);
    
    return ResponseEntity.ok().body(new ApiResponse(true, "회원가입이 성공적으로 되었습니다."));
  }
}