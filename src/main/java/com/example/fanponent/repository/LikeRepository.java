package com.example.fanponent.repository;

import com.example.fanponent.entity.Like;
import com.example.fanponent.entity.Member;
import com.example.fanponent.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

  Optional<Like> findByPostAndMember(Post post, Member member);
}
