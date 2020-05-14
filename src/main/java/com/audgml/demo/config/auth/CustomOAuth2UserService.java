// package com.audgml.demo.config.auth;

// import java.util.Collections;

// import javax.servlet.http.HttpSession;

// import com.audgml.demo.config.auth.dto.OAuthAttributes;
// import com.audgml.demo.config.auth.dto.SessionUser;
// import com.audgml.demo.domain.user.User;
// import com.audgml.demo.domain.user.UserRepository;

// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
// import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
// import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
// import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
// import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
// import org.springframework.security.oauth2.core.user.OAuth2User;
// import org.springframework.stereotype.Service;

// import lombok.RequiredArgsConstructor;

// @RequiredArgsConstructor
// @Service
// public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

//   private final UserRepository userRepository;
//   private final HttpSession httpSession; 
  
//   @Override
//   public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//     OAuth2UserService delegate = new DefaultOAuth2UserService();
//     OAuth2User oAuth2User = delegate.loadUser(userRequest);

//     //>>registrationId
//     //현재 로그인 진행중인 서비스를 구분하는 코드 
//     //지금은 구글만 사용하는 불필요한 값이지만 이후 네이버로그인연동 등 구분하기 위해 사용.
//     String registrationId = userRequest.getClientRegistration().getRegistrationId();
//     /** >>userNameAttributeName
//      * • OAuth2 로그인 진행 시 키가 되는 필드값을 이야기합니다 Primary Key와 같은 의 미입니다 
//      * • 구글의 경우 기본적으로 코드를 지원하지만， 네이버 카카오 등은 기본 지원하지 않습 니다 구글의 기본 코드는 “sub" 입니다 
//      * • 이후 네이버 로그인과 구글 로그인을 동시 지원할 때 사용됩니다 
//      */
//     String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
//             .getUserInfoEndpoint().getUserNameAttributeName();

//     /**
//      * >>attributes
//      * • OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스입니다 
//      * • 이후 네이버 등 다른 소설 로그인도 이 클래스를 사용합니다
//      */
//     OAuthAttributes attributes = OAuthAttributes
//             .of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

//     User user = saveOrUpdate(attributes);
//     //SessionUser 
//     // 세션에 사용자정보를 저장하기 위한 dto 클래스 
//     httpSession.setAttribute("user", new SessionUser(user));

//     return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey()))
//         , attributes.getAttributes()
//         , attributes.getNameAttributeKey());
//   }



//   private User saveOrUpdate(OAuthAttributes attributes) {
//     User user = userRepository.findByEmail(attributes.getEmail())
//                               .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
//                               .orElse(attributes.toEntity());
//     return userRepository.save(user); 
//   }

// }
 
