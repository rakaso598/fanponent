package com.example.fanponent.service;

import org.springframework.stereotype.Service;

@Service
public interface LikeService {
  void addLike(Long postId, Long memberId);
  void removeLike(Long postId, Long memberId);
  int getLikeCount(Long postId);
}
