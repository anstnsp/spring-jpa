// package com.audgml.demo.config.auth.dto;

// import java.util.Map;

// import com.audgml.demo.domain.user.Role;
// import com.audgml.demo.domain.user.User;

// import lombok.Builder;
// import lombok.Getter;

// //OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스 
// //이후 네이버등 다른 소셜 로그인도 이 클래스를 사용합니다. 
// @Getter
// public class OAuthAttributes {
//   private Map<String, Object> attributes;
//   private String nameAttributeKey;
//   private String name; 
//   private String email;
//   private String picture; 

//   @Builder
//   public OAuthAttributes(Map<String, Object> attributes,
//                         String nameAttributeKey, String name, 
//                         String email, String picture) {

//   this.attributes = attributes;
//   this.nameAttributeKey = nameAttributeKey;
//   this.name = name; 
//   this.email = email; 
//   this.picture = picture;
//   }

//   // [1] of()
//   // OAuth2User에서 반환하는 사용자 정보는 Map이기 때문에 값 하나하나를 반환해야함. 
//   public static OAuthAttributes of(String registrationId, String userNameAttributeName,  // [1]
//                                   Map<String, Object> attributes) {
//     return ofGoogle(userNameAttributeName, attributes);
//   }

//   private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
//     return OAuthAttributes.builder()
//           .name((String) attributes.get("name"))
//           .email((String) attributes.get("email"))
//           .picture((String) attributes.get("picture"))
//           .attributes(attributes)
//           .nameAttributeKey(userNameAttributeName)
//           .build();
//   }

//    // [2] toEntity()
//    // User 엔티터를 생성합니다 
//    // OAuthAttributes에서 엔티티를 생성하는 시점은 처음 가입할 때입니다 
//    // 가입할 때의 기본 권한을 GUEST로 주기 위해서 role 빌더값에는 Role.GUEST를 사용합니다. 
//    // OAuthAttributes 클래스 생성이 끝났으면 같은 패키지에 SessionUser 클래스를 생성합니다.
//   public User toEntity() {  // [2]
//     return User.builder()
//               .name(name)
//               .email(email)
//               .picture(picture)
//               .role(Role.GUEST) //가입할 때의 기본 권한을 GUEST로 주기 위해서 role빌더값에 Role.GUEST를 사용.
//               .build();
//   }

//   //OAuthAttribute클래스 생성 후 같은 패키지에 SessionUser클래스를 생성. 
// }