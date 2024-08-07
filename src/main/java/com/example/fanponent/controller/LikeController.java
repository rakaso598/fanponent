package com.example.fanponent.controller;

import com.example.fanponent.dto.LikeRequest;
import com.example.fanponent.entity.Post;
import com.example.fanponent.repository.PostRepository;
import com.example.fanponent.service.LikeService;
import com.example.fanponent.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/likes")
public class LikeController {

  @Autowired
  private PostService postService;

  @Autowired
  private LikeService likeService;

  @PostMapping("/like/{postId}")
  public ResponseEntity<Map<String, Object>> likePost(@PathVariable Long postId, @RequestBody Map<String, Long> request) {
    Long memberId = request.get("memberId");
    likeService.addLike(postId, memberId); // 좋아요 추가
    int likeCount = likeService.getLikeCount(postId); // 현재 좋아요 수 가져오기

    Map<String, Object> response = new HashMap<>();
    response.put("likeCount", likeCount);
    response.put("liked", true);

    return ResponseEntity.ok(response);
  }

  @PostMapping("/unlike/{postId}")
  public ResponseEntity<Map<String, Object>> unlikePost(@PathVariable Long postId, @RequestBody Map<String, Long> request) {
    Long memberId = request.get("memberId");
    likeService.removeLike(postId, memberId); // 좋아요 취소
    int likeCount = likeService.getLikeCount(postId); // 현재 좋아요 수 가져오기

    Map<String, Object> response = new HashMap<>();
    response.put("likeCount", likeCount);
    response.put("liked", false);

    return ResponseEntity.ok(response);
  }
}


