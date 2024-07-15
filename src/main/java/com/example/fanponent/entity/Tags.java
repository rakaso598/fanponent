package com.example.fanponent.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tags")
public class Tags {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long tagId;

  @Column(nullable = false)
  private String tagName;

  // PostTag 연결 일대다
  @OneToMany(mappedBy = "tags")
  private List<PostTags> postTags = new ArrayList<PostTags>();


}