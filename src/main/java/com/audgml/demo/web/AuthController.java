package com.audgml.demo.web;

import java.net.URI;

import javax.validation.Valid;

import com.audgml.demo.config.auth.dto.AuthProvider;
import com.audgml.demo.domain.user.User;
import com.audgml.demo.domain.user.UserRepository;
import com.audgml.demo.exception.BadRequestException;
import com.audgml.demo.security.TokenProvider;
import com.audgml.demo.web.dto.ApiResponse;
import com.audgml.demo.web.dto.AuthResponse;
import com.audgml.demo.web.dto.user.LoginRequestDto;
import com.audgml.demo.web.dto.user.SignupRequestDto;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

        private final AuthenticationManager authenticationManager;
        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final TokenProvider tokenProvider;

        @PostMapping("/login")
        public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {

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
                        throw new BadRequestException("Email address already in use.");
                }

                // Creating user's account
                User user = new User();
                user.setName(signUpRequestDto.getName());
                user.setEmail(signUpRequestDto.getEmail());
                user.setPassword(signUpRequestDto.getPassword());
                user.setProvider(AuthProvider.local);

                user.setPassword(passwordEncoder.encode(user.getPassword()));

                User result = userRepository.save(user);

                URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/me")
                                .buildAndExpand(result.getId()).toUri();

                return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully@"));
        }
}