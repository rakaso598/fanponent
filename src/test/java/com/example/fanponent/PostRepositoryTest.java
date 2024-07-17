package com.example.fanponent;

import com.example.fanponent.entity.Post;
import com.example.fanponent.entity.PostTag;
import com.example.fanponent.entity.Tag;
import com.example.fanponent.repository.PostRepository;
import com.example.fanponent.repository.PostTagRepository;
import com.example.fanponent.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PostRepositoryTest {
  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private TagRepository tagRepository;

  @Autowired
  private PostTagRepository postTagRepository;

  @Test
  public void testCreateAndFindPostTag() {
    // Post 생성
    Post post = new Post();
    post.setPostTitle("제목");
    post.setPostContent("내용");
    post.setCreatedAt(LocalDateTime.now());
    post.setUpdatedAt(LocalDateTime.now());
    entityManager.persist(post);

    // Tag 생성
    Tag tag = new Tag();
    tag.setTagName("태그명");
    entityManager.persist(tag);

    // PostTag 생성
    PostTag postTag = new PostTag();
    postTag.setPost(post);
    postTag.setTag(tag);
    entityManager.persist(postTag);

    // PostTag 조회
    PostTag foundPostTag = postTagRepository.findById(postTag.getId()).orElse(null);
    assertThat(foundPostTag).isNotNull();
    assertThat(foundPostTag.getPost().getPostId()).isEqualTo(post.getPostId());
    assertThat(foundPostTag.getTag().getTagId()).isEqualTo(tag.getTagId());
  }
}
