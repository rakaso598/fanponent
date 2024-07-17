package com.example.fanponent.repository;

import com.example.fanponent.entity.Post;
import com.example.fanponent.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

  List<Tag> findAll();

}
