package com.audgml.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//이 클래스는 사용자가 인증없이 보호 된 리소스에 액세스하려고 할 때 호출됩니다. 
//이 경우 간단히 401 Unauthorized 응답을 반환합니다.
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

  @Override
  public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
      AuthenticationException e) throws IOException, ServletException {
    logger.error("Responding with unauthorized error. Message - {}", e.getMessage());
    httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getLocalizedMessage());
  }
}