package com.example.fanponent.service;

import com.example.fanponent.entity.Like;
import com.example.fanponent.entity.Member;
import com.example.fanponent.entity.Post;
import com.example.fanponent.repository.LikeRepository;
import com.example.fanponent.repository.MemberRepository;
import com.example.fanponent.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class LikeService {

  @Autowired
  private LikeRepository likeRepository;

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private MemberRepository memberRepository;

  @Transactional
  public void likePost(Long postId, Long memberId) {
    Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
    Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));

    Like like = new Like();
    like.setPost(post);
    like.setMember(member);
    like.setCreatedAt(LocalDateTime.now());

    likeRepository.save(like);

    // 좋아요 수 증가
    post.setLikeCount(post.getLikeCount() + 1);
    postRepository.save(post);
  }


  @Transactional
  public void unlikePost(Long postId, Long memberId) {
    Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
    Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));

    Like like = likeRepository.findByPostAndMember(post, member)
        .orElseThrow(() -> new RuntimeException("Like not found"));

    likeRepository.delete(like);

    // 좋아요 수 감소
    post.setLikeCount(post.getLikeCount() - 1);
    postRepository.save(post);
  }




}
