package com.example.fanponent.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column
    private String postTitle;

    @Column
    private String postContent;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<PostTag> postTags = new ArrayList<>();

    private int likeCount;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Like> likes;

    // getTagNames 메서드 추가
    public String getTagNames() {
        return postTags.stream()
            .map(postTag -> postTag.getTag().getTagName())
            .collect(Collectors.joining(", "));
    }

}