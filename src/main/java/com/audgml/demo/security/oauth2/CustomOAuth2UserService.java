package com.audgml.demo.security.oauth2;

import java.util.Optional;

import com.audgml.demo.config.auth.dto.AuthProvider;
import com.audgml.demo.domain.user.User;
import com.audgml.demo.domain.user.UserRepository;
import com.audgml.demo.exception.OAuth2AuthenticationProcessingException;
import com.audgml.demo.security.UserPrincipal;
import com.audgml.demo.security.oauth2.user.OAuth2UserInfo;
import com.audgml.demo.security.oauth2.user.OAuth2UserInfoFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;

// The CustomOAuth2UserService extends Spring Security’s DefaultOAuth2UserService and 
// implements its loadUser() method. 
// This method is called after an access token is obtained from the OAuth2 provider.
// In this method, we first fetch the user’s details from the OAuth2 provider. 
// If a user with the same email already exists in our database then we update his details, 
// otherwise, we register a new user.
//이 메소드는 공급자(google,kakao)로부터 접근토큰 얻은 후에 실행된다. 
//처음 유저디테일 가져오고, 만약 디비에 같은 유저이메일이 존재하면 업데이트하고 아니면 새로 유저만듬. 
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  private static final Logger logger = LoggerFactory.getLogger(CustomOAuth2UserService.class);
  private final UserRepository userRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
    logger.info("#loadUser start# ");
    logger.info(
        "#####oAuth2UserRequest:" + oAuth2UserRequest.toString() + "#" + oAuth2UserRequest.getAccessToken().toString());
    OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

    try {
      return processOAuth2User(oAuth2UserRequest, oAuth2User);
    } catch (AuthenticationException ex) {
      logger.error("AuthenticationException : {}", ex);
      throw ex;
    } catch (Exception ex) {
      // Throwing an instance of AuthenticationException will trigger the
      // OAuth2AuthenticationFailureHandler
      throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
    }
  }

  private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
    logger.info("#processOAuth2User start# ");
    OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory
        .getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
    logger.info("oAuth2UserInfo.getEmail():" + oAuth2UserInfo.getEmail());

    if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
      throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
    }

    Optional<User> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
    User user;
    if (userOptional.isPresent()) {
      user = userOptional.get();

      if (!user.getProvider()
          .equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
        throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " + user.getProvider()
            + " account. Please use your " + user.getProvider() + " account to login.");
      }
      logger.info("##유저업데이트하기 전##");
      user = updateExistingUser(user, oAuth2UserInfo);
    } else {
      logger.info("##유저 생성하기전##");
      user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
    }
    logger.info("##유저생성바로전##");
    return UserPrincipal.create(user, oAuth2User.getAttributes());
  }

  private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
    User user = new User();

    user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
    user.setProviderId(oAuth2UserInfo.getId());
    user.setName(oAuth2UserInfo.getName());
    user.setEmail(oAuth2UserInfo.getEmail());
    user.setRole(user.getRole().USER);
    logger.info("user.getRole 111: {}", user.getRole().USER);
    // logger.info("user.getRole 222: {}", user.getRole().getKey());
    logger.info("user : {}", user.toString());
    // user.setImageUrl(oAuth2UserInfo.getImageUrl());
    return userRepository.save(user);
  }

  private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
    existingUser.setName(oAuth2UserInfo.getName());
    // existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
    return userRepository.save(existingUser);
  }
}