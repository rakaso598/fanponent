package com.example.fanponent.controller;

import com.example.fanponent.dto.PostDtoImpl;
import com.example.fanponent.entity.Member;
import com.example.fanponent.entity.Post;
import com.example.fanponent.entity.PostTag;
import com.example.fanponent.entity.Tag;
import com.example.fanponent.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/main")
    public String getRecentPosts(@RequestParam(defaultValue = "0") int page, Model model) {
        List<Post> recentPosts = postService.getRecentPosts(page, 10);
        List<PostDtoImpl> postDtos = recentPosts.stream().map(post -> {
            PostDtoImpl dto = new PostDtoImpl();
            dto.setPostTitle(post.getPostTitle());
            dto.setPostContent(post.getPostContent());
            dto.setMemberName(post.getMember().getMemberName());
            dto.setUpdatedAt(post.getUpdatedAt());

            // Tag 정보 세팅
            List<PostTag> postTags = post.getPostTags();
            if (!postTags.isEmpty()) {
                // 모든 태그 이름을 콤마로 구분하여 결합
                String tagNames = postTags.stream()
                    .map(postTag -> postTag.getTag().getTagName())
                    .collect(Collectors.joining(", "));
                dto.setTagNames(tagNames);
            }

            return dto;
        }).collect(Collectors.toList());

        model.addAttribute("posts", postDtos);

        return "post-list";  // Thymeleaf 템플릿의 이름
    }



}
