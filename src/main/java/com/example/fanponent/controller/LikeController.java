package com.example.fanponent.controller;

import com.example.fanponent.dto.LikeRequest;
import com.example.fanponent.entity.Post;
import com.example.fanponent.repository.PostRepository;
import com.example.fanponent.service.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/likes")
public class LikeController {

  private final LikeService likeService;
  private final PostRepository postRepository;


  @Autowired
  public LikeController(LikeService likeService, PostRepository postRepository) {
    this.likeService = likeService;
    this.postRepository = postRepository;
  }



  @PostMapping("/{postId}")
  @ResponseBody
  public Map<String, Object> likePost(@PathVariable Long postId, @RequestBody LikeRequest likeRequest) {
    log.debug("Received like request for postId: {}, memberId: {}", postId, likeRequest.getMemberId());
    Map<String, Object> response = new HashMap<>();
    try {
      Optional<Post> postOptional = postRepository.findUniquePost(postId);
      if (postOptional.isPresent()) {
        int likeCount = likeService.likePost(postId, likeRequest.getMemberId());
        response.put("likeCount", likeCount);
      } else {
        response.put("error", "Post not found");
      }
    } catch (Exception e) {
      log.error("Error processing like request", e);
      response.put("error", e.getMessage());
    }
    return response;
  }


  @DeleteMapping("/{postId}")
  public void unlikePost(@PathVariable Long postId, @RequestParam Long memberId) {
    likeService.unlikePost(postId, memberId);
  }
}

