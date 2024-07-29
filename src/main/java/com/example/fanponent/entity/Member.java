package com.example.fanponent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "member")
public class Member extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long memberId;

  @Column(nullable = false)
  private String memberName;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(updatable = false)
  private LocalDateTime createdAt;

  @Column
  private LocalDateTime updatedAt;

  @Column(nullable = false)
  private Role role;

  @Column
  private String profilePictureUrl;

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<Post> posts = new ArrayList<>();

  public Member update(String name, String picture) {
    this.memberName = name;
    this.profilePictureUrl = picture;
    return this;
  }

  public String getRoleKey() {
    return this.role.getKey();
  }

  /*

  Member member = Member.builder()
    .memberName("John Doe")
    .email("john.doe@example.com")
    .password("password123")
    .role(1L)
    .profilePictureUrl("http://example.com/profile.jpg")
    .build();

   */
}
