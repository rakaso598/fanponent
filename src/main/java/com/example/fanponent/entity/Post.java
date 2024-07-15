package com.example.fanponent.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String postTitle;

    @Column(nullable = false)
    private String memberName;

    @Column
    private String postContent;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;


    // PostTag 연결 일대다
    @OneToMany(mappedBy = "post")
    private List<PostTags> postTags = new ArrayList<PostTags>();


}