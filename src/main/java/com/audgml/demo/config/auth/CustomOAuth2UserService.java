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

// /**
//  * [소셜 로그인 성공 후 후속조치를 진행할 OAuth2UserService 인터페이스의 구현체]
//  * - 리소스서버(소셜서비스들)에서 사용자 정보를 가져온 후 추가로 진행하고자 하는 기능 명시. 
//  * - 이 클래스에서는 구글 로그인 후 가져온 사용자의 정보(email, name, pricture 등)들을 기반으로 
//  * - 회원가입 및 정보수정, 세션저장 등의 기능을 지원. 
//  * 
//  */
// @RequiredArgsConstructor
// @Service
// public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

//   private final UserRepository userRepository;
//   private final HttpSession httpSession; 
  
//   @Override
//   public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//     OAuth2UserService delegate = new DefaultOAuth2UserService();
//     OAuth2User oAuth2User = delegate.loadUser(userRequest);

//     //[1] registrationId
//     //현재 로그인 진행중인 서비스를 구분하는 코드 
//     //지금은 구글만 사용하는 불필요한 값이지만 이후 네이버로그인연동 등 구분하기 위해 사용.
//     String registrationId = userRequest.getClientRegistration().getRegistrationId(); // [1]
//     /** [2] userNameAttributeName
//      * • OAuth2 로그인 진행 시 키가 되는 필드값을 이야기합니다 Primary Key와 같은 의미입니다.
//      * • 구글의 경우 기본적으로 코드를 지원하지만， 네이버 카카오 등은 기본 지원하지 않습 니다 구글의 기본 코드는 “sub" 입니다 
//      * • 이후 네이버 로그인과 구글 로그인을 동시 지원할 때 사용됩니다 
//      */
//     String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails() // [2]
//             .getUserInfoEndpoint().getUserNameAttributeName();

//     /**
//      *   [3] attributes
//      * • OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스입니다 
//      * • 이후 네이버 등 다른 소설 로그인도 이 클래스를 사용합니다
//      */
//     OAuthAttributes attributes = OAuthAttributes
//             .of(registrationId, userNameAttributeName, oAuth2User.getAttributes()); // [3]

//     User user = saveOrUpdate(attributes);
//     // [4] SessionUser 
//     // 세션에 사용자정보를 저장하기 위한 dto 클래스 
//     // 왜 User클래스를 쓰지 않고 새로 만들어서 쓰는지 생각해보세요!!.
//     httpSession.setAttribute("user", new SessionUser(user)); // [4]

//     return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey()))
//         , attributes.getAttributes()
//         , attributes.getNameAttributeKey());
//   }


//   //구글 사용자 정보가 업데이트 되었을때를 대비하여 update기능도 구현. 
//   //사용자의 이름(name)이나 사진(picture)가 변경되면 User엔티티에도 반영됩니다. 
//   private User saveOrUpdate(OAuthAttributes attributes) { // [5]
//     User user = userRepository.findByEmail(attributes.getEmail())
//                               .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
//                               .orElse(attributes.toEntity());
//     return userRepository.save(user); 
//   }

//   //이후 OAuthAttributes클래스 생성하러가기. 
// }
 
