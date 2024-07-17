package com.example.fanponent.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Data
@Entity
@Table(name = "tag")
public class Tag {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long tagId;

  @Column
  private String tagName;

  @OneToMany(mappedBy = "tag")
  private List<PostTag> postTags = new ArrayList<>();


}