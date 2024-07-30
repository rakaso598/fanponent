package com.example.fanponent.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
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

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String role;

  @Column
  private String profilePictureUrl;

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  @Builder.Default
  private List<Post> posts = new ArrayList<>();

  public Member update(String name, String picture) {
    this.memberName = name;
    this.profilePictureUrl = picture;
    return this;
  }

}
