package com.example.fanponent.service;

public interface LikeService {
  int likePost(Long postId, Long memberId);
  void unlikePost(Long postId, Long memberId);
}
