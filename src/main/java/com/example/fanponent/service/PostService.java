package com.example.fanponent.service;

import com.example.fanponent.dao.PostsRepository;
import com.example.fanponent.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostsRepository postsRepository;

    @Autowired
    public PostService(PostsRepository postDao) {
        this.postsRepository = postDao;
    }

    public List<Post> getAllPosts() {
        return postsRepository.findAll();
    }
}
