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

    @Column
    private int likeCount = 0; // 기본값 설정

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<PostTag> postTags = new ArrayList<>();

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Like> likes;

    // getTagNames 메서드 추가
    public String getTagNames() {
        return postTags.stream()
            .map(postTag -> postTag.getTag().getTagName())
            .collect(Collectors.joining(", "));
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    // getMemberId 메서드 추가
    public Long getMemberId() {
        if (member != null) {
            return member.getMemberId();
        } else {
            throw new IllegalStateException("Post does not have an associated member");
        }
    }

    // setMemberId 메서드 추가
    public void setMemberId(Long memberId) {
        if (this.member == null) {
            this.member = new Member();
        }
        this.member.setMemberId(memberId);
    }

    // getMemberName 메서드 추가
    public String getMemberName() {
        if (member != null) {
            return member.getMemberName();
        } else {
            throw new IllegalStateException("Post does not have an associated member");
        }
    }

    // setMemberName 메서드 추가
    public void setMemberName(String memberName) {
        if (this.member == null) {
            this.member = new Member();
        }
        this.member.setMemberName(memberName);
    }

}