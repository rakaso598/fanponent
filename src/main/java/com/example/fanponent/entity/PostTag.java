package com.example.fanponent.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "posttag")
public class PostTag {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "post_id")
  private Post post;

  @ManyToOne
  @JoinColumn(name = "tag_id")
  private Tag tag;

}