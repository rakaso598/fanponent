package com.example.fanponent.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
  USER("ROLE_USER", "일반사용자"),
  ADMIN("ROLE_ADMIN", "관리자");

  private final String key;
  private final String title;

  public String getKey() {
    return key;
  }
}
