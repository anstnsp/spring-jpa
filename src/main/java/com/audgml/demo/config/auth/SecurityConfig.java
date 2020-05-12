package com.audgml.demo.config.auth;

import com.audgml.demo.domain.user.Role;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebMvcSecurity //Spring Security 설정들을 활성화 
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final CustomOAuth2UserService customOAuth2UserService; 

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
    .headers().frameOptions().disable() // h2-console화면을 사용하기 위해 해당 옵션들을 disable 
    .and()
    .authorizeRequests() //URL별 권한 관리를 설정하는 옵션의 시작점. 
    //authorizeRequsets()가 선언되어야만 antMatchers 옵션을 사용가능.  
    //antMatchers는 권한 관리 대상을 지정하는 옵션. 
    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll() //이경로는 전체열람
    .antMathcers("/api/v1/**").hasRole(Role.USER.name()) //해당 url은 USER권한 가진사람만 가능 
    .anyRequest().authenticated()   //anyReqeust는 설정된 값들 이외의 나머지 URL들 나타냄. 
    //여기서는 .authenticated()를 추가해 나머지 url들은 모두 인즈된 사용자들에게만 허용 
    .and()
    .logout()
    .logoutSuccessUrl("/") //로그아웃 성공시 / 주소로 이동 
    .and()
    .oauth2Login() //oauth2Login : OAuth2 로그인 기능에 대한 여러 설정의 진입점. 
    .userInfoEndpoint() //OAuth2로그인 성공 이후 사용자 정보를 가져올 때의 설정들 담당.
    .userService(customOAuth2UserService);  
    /**
     * • 소설 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록 합니다 
     * • 리소스 서버(즉,소설 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고 자하는 기능을 명시할수 있습니다
     */
  }
}