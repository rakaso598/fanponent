package com.example.fanponent.controller;

import com.example.fanponent.config.SessionMember;
import com.example.fanponent.dto.PostDto;
import com.example.fanponent.dto.SessionMemberDtoImpl;
import com.example.fanponent.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping
public class PostController {

  private final PostService postService;
  private final HttpSession httpSession;

  @Autowired
  public PostController(PostService postService, HttpSession httpSession) {
    this.postService = postService;
    this.httpSession = httpSession;
  }

  @GetMapping("/posts")
  public String getAllPosts(Model model, @RequestParam(value = "continue", required = false) String continueParam) {
    log.debug("continueParam: {}", continueParam);
    List<PostDto> postdtos = postService.getAllPosts();
    model.addAttribute("postdtos", postdtos);

    // 세션에 member 정보 추가하는 로직
    SessionMember member = (SessionMember) httpSession.getAttribute("member");
    if (member != null) {
      SessionMemberDtoImpl sessionMemberDtoImpl = new SessionMemberDtoImpl(member);
      model.addAttribute("member", sessionMemberDtoImpl); // DTO 객체를 모델에 추가
    }
    model.addAttribute("continue", continueParam);
    return "post-list";  // Thymeleaf 템플릿의 이름
  }

}


