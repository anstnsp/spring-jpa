package com.audgml.demo.security.oauth2.user;

import java.util.Map;

import org.json.simple.JSONObject;

import javassist.bytecode.Descriptor.Iterator;

public class NaverOauth2UserInfo extends OAuth2UserInfo {
  
  public NaverOauth2UserInfo(Map<String, Object> attributes) {
    super(attributes); 
  }

  @Override
  public String getId() {
    JSONObject json  =new JSONObject((Map)attributes.get("response"));
    return json.get("id").toString();
  }
 
  @Override
  public String getName() {
    JSONObject json  =new JSONObject((Map)attributes.get("response"));
    return json.get("name").toString();
  }

  @Override
  public String getEmail() {
    JSONObject json  =new JSONObject((Map)attributes.get("response"));
    return json.get("email").toString();
  }

  @Override
  public String getImageUrl() {
      return (String) attributes.get("profile_image");
  }


}