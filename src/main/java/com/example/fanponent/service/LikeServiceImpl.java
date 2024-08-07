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

import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private LikeRepository likeRepository;

  @Autowired
  private MemberRepository memberRepository;

  @Override
  @Transactional
  public void addLike(Long postId, Long memberId) {
    Optional<Post> postOptional = postRepository.findById(postId);
    Optional<Member> memberOptional = memberRepository.findById(memberId);

    if (postOptional.isPresent() && memberOptional.isPresent()) {
      Like like = new Like();
      like.setPost(postOptional.get());
      like.setMember(memberOptional.get());
      likeRepository.save(like);
    } else {
      // Handle the case where post or member is not found
      throw new IllegalArgumentException("Post or Member not found");
    }
  }

  @Override
  @Transactional
  public void removeLike(Long postId, Long memberId) {
    Optional<Post> postOptional = postRepository.findById(postId);
    Optional<Member> memberOptional = memberRepository.findById(memberId);

    if (postOptional.isPresent() && memberOptional.isPresent()) {
      Optional<Like> existingLike = likeRepository.findByPostAndMember(postOptional.get(), memberOptional.get());
      existingLike.ifPresent(likeRepository::delete);
    } else {
      // Handle the case where post or member is not found
      throw new IllegalArgumentException("Post or Member not found");
    }
  }

  @Override
  public int getLikeCount(Long postId) {
    Optional<Post> postOptional = postRepository.findById(postId);
    return postOptional.map(post -> likeRepository.countByPost(post)).orElse(0);
  }
}
