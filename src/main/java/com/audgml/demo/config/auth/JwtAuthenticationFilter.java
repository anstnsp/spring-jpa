// package com.audgml.demo.config.auth;

// import lombok.RequiredArgsConstructor;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.filter.GenericFilterBean;

// import javax.servlet.FilterChain;
// import javax.servlet.ServletException;
// import javax.servlet.ServletRequest;
// import javax.servlet.ServletResponse;
// import javax.servlet.http.HttpServletRequest;
// import java.io.IOException;
// import java.util.Optional;

// //토큰을 생성하고 검증하는 컴포넌트를 완성했지만 실제로 
// //이 컴포넌트를 이용하는 것은 인증 작업을 진행하는 Filter 입니다. 
// //이 필터는 검증이 끝난 JWT로부터 유저정보를 받아와서 UsernamePasswordAuthenticationFilter 로
// //전달해야 할 것입니다. 
// @RequiredArgsConstructor
// public class JwtAuthenticationFilter extends GenericFilterBean {

//   private final JwtTokenProvider jwtTokenProvider;

//   @Override
//   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//       throws IOException, ServletException {
//     // 헤더에서 JWT를 받아옵니다. resolveToken이 헤더에서 토큰빼오는거
//     String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
//     // 유효한토큰인지 확인.
//     //아래의 if문을 Optional 람다식으로 교체. 
//     Optional.ofNullable(token)
//             .filter(t -> jwtTokenProvider.validateToken(t))
//             .map(t -> jwtTokenProvider.getAuthentication(t))
//             .ifPresent(auth -> SecurityContextHolder.getContext().setAuthentication(auth));

//     // if (token != null && jwtTokenProvider.validateToken(token)) {
//     //   // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
//     //   Authentication authentication = jwtTokenProvider.getAuthentication(token);
//     //   // SecurityContext 에 Authentication 객체를 저장합니다.
//     //   SecurityContextHolder.getContext().setAuthentication(authentication);
//     // }
//     chain.doFilter(request, response);
//   }
// }