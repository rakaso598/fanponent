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
@Table(name = "TAGS")
public class Tag {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long tagId;

  @Column(nullable = false)
  private String tagName;

  // PostTag 연결 일대다
  @OneToMany(mappedBy = "tag")
  private List<PostTag> postTags = new ArrayList<PostTag>();

  // Getter Setter


  public Long getTagId() {
    return tagId;
  }

  public void setTagId(Long tagId) {
    this.tagId = tagId;
  }

  public String getTagName() {
    return tagName;
  }

  public void setTagName(String tagName) {
    this.tagName = tagName;
  }

  public List<PostTag> getPostTags() {
    return postTags;
  }

  public void setPostTags(List<PostTag> postTags) {
    this.postTags = postTags;
  }
}