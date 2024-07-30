package com.example.fanponent.oauth;

import com.example.fanponent.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
  private Map<String, Object> attributes;
  private String nameAttributeKey;
  private String name;
  private String email;
  private String picture;

  @Builder
  public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
    this.attributes = attributes;
    this.nameAttributeKey = nameAttributeKey;
    this.name = name;
    this.email = email;
    this.picture = picture;
  }

  public OAuthAttributes(String name, String email, Map<String, Object> attributes) {
  }

  public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
    if ("google".equals(registrationId)) {
      return ofGoogle(userNameAttributeName, attributes);
    }
    return null; // 다른 OAuth 제공자에 대한 처리가 필요할 경우 추가
  }

  private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
    return OAuthAttributes.builder()
        .name((String) attributes.get("name"))
        .email((String) attributes.get("email"))
        .picture((String) attributes.get("picture"))
        .attributes(attributes)
        .nameAttributeKey(userNameAttributeName)
        .build();
  }

  public Member toEntity() {
    return Member.builder()
        .memberName(name)
        .email(email)
        .profilePictureUrl(picture)
        .role("ROLE_USER") // 기본 권한 설정
        .build();
  }

}
