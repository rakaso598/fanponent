package com.example.fanponent.repository;

import com.example.fanponent.entity.Post;
import com.example.fanponent.entity.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostTagRepository extends JpaRepository<PostTag, Long> {

  List<PostTag> findAll();

}
