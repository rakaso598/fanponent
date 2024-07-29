package com.example.fanponent.config;

import com.example.fanponent.entity.Member;

import java.io.Serializable;

public class SessionMember implements Serializable {
  private String name;
  private String email;
  private String picture;

  public SessionMember(Member member) {
    this.name = member.getMemberName();
    this.email = member.getEmail();
    this.picture = member.getProfilePictureUrl();
  }

  // Getters
  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPicture() {
    return picture;
  }
}
