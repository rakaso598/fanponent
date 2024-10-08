package com.example.fanponent.config;

import com.example.fanponent.entity.Member;

import java.io.Serializable;

public class SessionMember implements Serializable {
  private Long memberId;
  private String memberName;
  private String email;
  private String profilePicture;

  public SessionMember(Member member) {
    this.memberId = member.getMemberId(); // memberId 설정
    this.memberName = member.getMemberName();
    this.email = member.getEmail();
    this.profilePicture = member.getProfilePictureUrl();
  }

  // Getters
  public Long getMemberId() {
    return memberId;
  }

  public String getMemberName() {
    return memberName;
  }

  public String getEmail() {
    return email;
  }

  public String getProfilePicture() {
    return profilePicture;
  }
}