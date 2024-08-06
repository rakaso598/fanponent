package com.example.fanponent.repository;

import com.example.fanponent.entity.Post;
import java.util.Optional;

public interface PostRepositoryCustom {
  Optional<Post> findUniquePost(Long postId);
}