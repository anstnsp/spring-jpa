package com.audgml.demo.security.oauth2.user;

import java.util.Map;

import org.json.simple.JSONObject;

public class KakaoOauth2UserInfo extends OAuth2UserInfo {

  public KakaoOauth2UserInfo(Map<String, Object> attributes) {
    super(attributes); 
  }
  
  @Override
  public String getId() {
      return ((Integer) attributes.get("id")).toString();
  }
 
  @Override
  public String getName() {
    JSONObject json  =new JSONObject((Map) attributes.get("properties"));
      return json.get("nickname").toString();
  }

  @Override
  public String getEmail() {
    JSONObject json  =new JSONObject((Map) attributes.get("kakao_account"));
    System.out.println(json.get("email"));
    System.out.println("두번:"+attributes.get("kakao_account"));
    System.out.println(attributes.entrySet().toString());
    return json.get("email").toString();
  }

  @Override
  public String getImageUrl() {
      return (String) attributes.get("avatar_url");
  }


  
}