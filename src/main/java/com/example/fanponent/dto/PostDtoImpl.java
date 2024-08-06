package com.example.fanponent.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostDtoImpl implements PostDto {

    Long postId = null;

    String postTitle = null; // 제목

    String postContent = null; // 내용

    Long memberId = null; // 멤버 아이디

    String memberName = null; // 유저 닉네임

    String tagNames = null; // 태그명 여러개

    LocalDateTime updatedAt = null; // 수정 시각



    public String getTagNames() {
        return tagNames;
    }
}
