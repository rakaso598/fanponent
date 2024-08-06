package com.example.fanponent.dto;

import java.time.LocalDateTime;

public interface PostDto {

    Long postId = null; // 게시글 아이디
    String postTitle = null; // 제목
    String postContent = null; // 내용

    Long memberId = null; // 멤버 아이디
    String memberName = null; // 멤버 닉네임
    String tagName = null; // 태그명
    LocalDateTime updatedAt = null; // 수정 시각
    int likeCount = 0;

}
