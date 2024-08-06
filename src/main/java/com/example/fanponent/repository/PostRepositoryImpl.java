package com.example.fanponent.repository;

import com.example.fanponent.entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class PostRepositoryImpl implements PostRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  @Transactional
  public Optional<Post> findUniquePost(Long postId) {
    List<Post> results = entityManager.createQuery("SELECT p FROM Post p WHERE p.postId = :postId", Post.class)
        .setParameter("postId", postId)
        .getResultList();
    if (results.isEmpty()) {
      return Optional.empty();
    } else if (results.size() > 1) {
      // 첫 번째 결과를 반환하거나 필요에 따라 처리
      return Optional.of(results.get(0));
    } else {
      return Optional.of(results.get(0));
    }
  }


}