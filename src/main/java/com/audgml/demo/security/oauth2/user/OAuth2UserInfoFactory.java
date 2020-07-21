package com.audgml.demo.security.oauth2.user;

import java.util.Map;

import com.audgml.demo.config.auth.dto.AuthProvider;
import com.audgml.demo.exception.OAuth2AuthenticationProcessingException;

public class OAuth2UserInfoFactory {
  public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
    if (registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
      return new GoogleOAuth2UserInfo(attributes);
    } else if(registrationId.equalsIgnoreCase(AuthProvider.kakao.toString())) {
      
      return new KakaoOauth2UserInfo(attributes);
    } else {
      throw new OAuth2AuthenticationProcessingException(
          "Sorry! Login with " + registrationId + " is not supported yet.");
    }
  }
}