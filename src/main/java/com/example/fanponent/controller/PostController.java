package com.example.fanponent.controller;

import com.example.fanponent.dto.PostDtoImpl;
import com.example.fanponent.entity.Post;
import com.example.fanponent.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Slf4j
@Controller
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/main")
    public String getAllPosts(Model model){
        List<Post> allPosts = postService.getAllPosts();

//        for (Post post : allPosts) {
//            List<PostTag> postTags = post.getPostTags();
//            // postTags를 활용하여 태그 정보를 처리하세요.
//            // ...
//        }

        model.addAttribute("allPosts", allPosts);
        return "post-list";
    }


    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostDtoImpl postDto) {
        // Convert PostDtoImpl to Post entity
        Post post = new Post();
        post.setPostTitle(postDto.getPostTitle());
        post.setMemberName(postDto.getMemberName());
        post.setPostContent(postDto.getPostContent());
        post.setUpdatedAt(postDto.getUpdatedAt());

        // Save the post using the service
//        postService.savePost(post);

        return ResponseEntity.ok("Post created successfully");
    }





    @GetMapping("/list-test")
    public String testList(Model model){
        System.out.println("getAllPosts() 호출!");

        List<Post> allPosts = postService.getAllPosts();

        model.addAttribute("allPosts",allPosts);


        return "list-test";
    }





}
