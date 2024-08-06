package com.example.fanponent.service;

import com.example.fanponent.dto.PostDto;
import com.example.fanponent.entity.Post;

import java.util.List;

public interface PostService {
  List<PostDto> getAllPosts(); // 추가된 메서드
}
