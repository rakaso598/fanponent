package com.example.fanponent.oauth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {
  private String name;
  private String email;
  private Map<String, Object> attributes;

  public CustomOAuth2User(String name, String email, Map<String, Object> attributes) {
    this.name = name;
    this.email = email;
    this.attributes = attributes;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return attributes;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(new SimpleGrantedAuthority("USER"));
  }

  @Override
  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }
}
