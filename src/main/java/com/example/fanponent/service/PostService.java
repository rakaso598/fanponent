package com.example.fanponent.service;

import com.example.fanponent.dto.PostDto;
import com.example.fanponent.entity.Post;

import java.util.List;

public interface PostService {
  List<Post> getRecentPosts(int page, int size);
  List<PostDto> getAllPosts(); // 추가된 메서드
}
