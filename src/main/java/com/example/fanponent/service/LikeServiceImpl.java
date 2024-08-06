package com.example.fanponent.service;

import com.example.fanponent.entity.Like;
import com.example.fanponent.repository.LikeRepository;
import com.example.fanponent.repository.MemberRepository;
import com.example.fanponent.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

  private final LikeRepository likeRepository;
  private final PostRepository postRepository;
  private final MemberRepository memberRepository;

  @Autowired
  public LikeServiceImpl(LikeRepository likeRepository, PostRepository postRepository, MemberRepository memberRepository) {
    this.likeRepository = likeRepository;
    this.postRepository = postRepository;
    this.memberRepository = memberRepository;
  }

  @Override
  public int likePost(Long postId, Long memberId) {
    Optional<Like> existingLike = likeRepository.findByPost_PostIdAndMember_MemberId(postId, memberId);
    if (existingLike.isPresent()) {
      // 이미 좋아요를 누른 상태라면 좋아요 취소
      likeRepository.delete(existingLike.get());
    } else {
      // 좋아요 추가
      Like like = new Like();
      like.setPost(postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found")));
      like.setMember(memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found")));
      likeRepository.save(like);
    }
    return likeRepository.countByPost_PostId(postId);
  }

  @Override
  public void unlikePost(Long postId, Long memberId) {
    Optional<Like> existingLike = likeRepository.findByPost_PostIdAndMember_MemberId(postId, memberId);
    existingLike.ifPresent(likeRepository::delete);
  }


}

