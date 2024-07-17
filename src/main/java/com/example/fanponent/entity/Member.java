package com.example.fanponent.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "member")
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long memberId;

  @Column
  private String memberName;

  @Column
  private String email;

  @Column
  private String password;

  @Column
  private LocalDateTime createdAt;

  @Column
  private LocalDateTime updatedAt;

  @Column
  private Long role;

  @Column
  private String profilePictureUrl;

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<Post> posts = new ArrayList<>();


}