package com.example.fanponent.service;

import com.example.fanponent.dto.PostDto;
import com.example.fanponent.dto.PostDtoImpl;
import com.example.fanponent.entity.Post;
import com.example.fanponent.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
  private final PostRepository postRepository;

  @Autowired
  public PostServiceImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Override
  public List<Post> getRecentPosts(int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
    Page<Post> postPage = postRepository.findAll(pageable);
    return postPage.getContent();
  }

  @Override
  public List<PostDto> getAllPosts() {
    List<Post> posts = postRepository.findAll();
    return posts.stream().map(post -> {
      PostDtoImpl postDto = new PostDtoImpl(); // PostDtoImpl 객체 생성
      postDto.setPostId(post.getPostId());
      postDto.setPostTitle(post.getPostTitle());
      postDto.setPostContent(post.getPostContent());
      postDto.setMemberId(post.getMember().getMemberId());
      postDto.setMemberName(post.getMember().getMemberName());
      postDto.setTagNames(post.getTagNames());
      postDto.setUpdatedAt(post.getUpdatedAt());
      postDto.setLikeCount(post.getLikeCount());
      return postDto;
    }).collect(Collectors.toList());
  }


}
