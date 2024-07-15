package com.example.fanponent.dto;

import java.time.LocalDateTime;

public interface PostDto {

    String postTitle = null; // 제목
    String memberName = null; // 유저 닉네임
    String postContent = null; // 내용
    LocalDateTime updatedAt = null; // 수정 시각
}
