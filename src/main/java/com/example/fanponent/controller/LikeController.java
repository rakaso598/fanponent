package com.example.fanponent.controller;

import com.example.fanponent.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {

  @Autowired
  private LikeService likeService;

  @PostMapping("/like")
  public void likePost(@RequestParam Long postId, @RequestParam Long memberId) {
    likeService.likePost(postId, memberId);
  }

  @DeleteMapping("/unlike")
  public void unlikePost(@RequestParam Long postId, @RequestParam Long memberId) {
    likeService.unlikePost(postId, memberId);
  }

}
