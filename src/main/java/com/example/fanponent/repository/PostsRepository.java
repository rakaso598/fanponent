package com.example.fanponent.repository;

import com.example.fanponent.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostsRepository extends JpaRepository<Post, Long> {

    Optional<Post> findById(Long id);

    List<Post> findAll();
}
