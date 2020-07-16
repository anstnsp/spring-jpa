package com.audgml.demo.config.auth;

import com.audgml.demo.security.CustomUserDetailsService;
import com.audgml.demo.security.RestAuthenticationEntryPoint;
import com.audgml.demo.security.TokenAuthenticationFilter;
import com.audgml.demo.security.oauth2.CustomOAuth2UserService;
import com.audgml.demo.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.audgml.demo.security.oauth2.OAuth2AuthenticationFailureHandler;
import com.audgml.demo.security.oauth2.OAuth2AuthenticationSuccessHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final CustomUserDetailsService customUserDetailsService;
  private final CustomOAuth2UserService customOAuth2UserService;
  private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
  private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
  private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

  @Bean
  public TokenAuthenticationFilter tokenAuthenticationFilter() {
    return new TokenAuthenticationFilter();
  }

  /**
   * Spring OAuth2 에서는 기본적으로 권한부여요청을 저장하기 위해
   * HttpSessionOAuth2AuthorizationRequestRepository 사용한다. 그러나 우리의 서비스는
   * stateless하기때문에 그걸 세션에 저장하지 않는다. 대신에 우리는 base64로 인코딩된 쿠키를 저장할거다.
   * 
   * @return
   */
  /*
   * By default, Spring OAuth2 uses
   * HttpSessionOAuth2AuthorizationRequestRepository to save the authorization
   * request. But, since our service is stateless, we can't save it in the
   * session. We'll save the request in a Base64 encoded cookie instead.
   */
  @Bean
  public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
    return new HttpCookieOAuth2AuthorizationRequestRepository();
  }

  @Override
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean(BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().headers().frameOptions().disable().and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable().formLogin().disable().httpBasic()
        .disable().exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint()).and()
        .authorizeRequests()
        .antMatchers("/", "/error", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.jpg", "/**/*.html",
            "/**/*.css", "/**/*.js")
        .permitAll()
        .antMatchers("/auth/**", "/oauth2/**", "/api/v1/posts/**", "/h2-console/**", "/user/signup", "/user/signin")
        .permitAll().anyRequest().authenticated().and().oauth2Login().authorizationEndpoint()
        .baseUri("/oauth2/authorize").authorizationRequestRepository(cookieAuthorizationRequestRepository()).and()
        .redirectionEndpoint().baseUri("/oauth2/callback/*").and().userInfoEndpoint()
        .userService(customOAuth2UserService).and().successHandler(oAuth2AuthenticationSuccessHandler)
        .failureHandler(oAuth2AuthenticationFailureHandler);

    // Add our custom Token based authentication filter
    http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }
}