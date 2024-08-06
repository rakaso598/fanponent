package com.example.fanponent.service;

import com.example.fanponent.entity.Like;
import com.example.fanponent.entity.Post;
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
    Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));

    if (existingLike.isPresent()) {
      // 이미 좋아요를 누른 상태라면 좋아요 취소
      likeRepository.delete(existingLike.get());
      post.setLikeCount(post.getLikeCount() - 1); // 좋아요 개수 감소
    } else {
      // 좋아요 추가
      Like like = new Like();
      like.setPost(post);
      like.setMember(memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found")));
      likeRepository.save(like);
      post.setLikeCount(post.getLikeCount() + 1); // 좋아요 개수 증가
    }
    postRepository.save(post); // 업데이트된 좋아요 개수 저장
    return post.getLikeCount(); // 좋아요 개수 반환
  }


  @Override
  public void unlikePost(Long postId, Long memberId) {
    Optional<Like> existingLike = likeRepository.findByPost_PostIdAndMember_MemberId(postId, memberId);
    existingLike.ifPresent(likeRepository::delete);
  }


}

