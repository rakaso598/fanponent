package com.example.fanponent.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostDtoImpl implements PostDto {

    String postTitle = null;
    String memberName = null;
    String postContent = null;
    LocalDateTime updatedAt = null;

}
