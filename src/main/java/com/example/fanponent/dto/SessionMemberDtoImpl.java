package com.example.fanponent.dto;

import com.example.fanponent.config.SessionMember;
import com.example.fanponent.entity.Member;

import java.io.Serializable;

public class SessionMemberDtoImpl implements SessionMemberDto {
  private Long memberId;
  private String memberName;
  private String email;
  private String profilePicture;

  public SessionMemberDtoImpl(SessionMember member) {
    this.memberId = member.getMemberId(); // memberId 설정
    this.memberName = member.getMemberName();
    this.email = member.getEmail();
    this.profilePicture = member.getProfilePicture();
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
