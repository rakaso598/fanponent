package com.example.fanponent.controller;

import com.example.fanponent.config.SessionMember;
import com.example.fanponent.dto.PostDtoImpl;
import com.example.fanponent.entity.Post;
import com.example.fanponent.entity.PostTag;
import com.example.fanponent.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;
    private final HttpSession httpSession;

    @GetMapping("/main")
    public String getRecentPosts(@RequestParam(value = "continue", required = false, defaultValue = "0") Integer page,
                                 @RequestParam(value = "continue", required = false) String continueParam, Model model) {
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

        // 세션에 member 정보 추가하는 로직
        SessionMember member = (SessionMember) httpSession.getAttribute("member");
        if (member != null) {
            model.addAttribute("userName", member.getName());
        }
        model.addAttribute("continue", continueParam);
        return "post-list";  // Thymeleaf 템플릿의 이름
    }

}
