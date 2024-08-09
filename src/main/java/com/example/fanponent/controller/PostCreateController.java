package com.example.fanponent.controller;

import com.example.fanponent.entity.Member;
import com.example.fanponent.entity.Post;
import com.example.fanponent.repository.MemberRepository;
import com.example.fanponent.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class PostCreateController {
  @Autowired
  private PostRepository postRepository;

  @Autowired
  private MemberRepository memberRepository;

  @GetMapping("/posts/createform")
  public String showCreateForm(Model model, Principal principal) {
    // 인증되지 않은 유저 처리
    if (principal == null) {
      return "redirect:/login";
    }

    // 로그인한 유저의 ID 가져오기
    String memberId = principal.getName();

    model.addAttribute("memberId", memberId);
    model.addAttribute("post", new Post());

    return "create-form";
  }

  @PostMapping("/posts/create")
  public String createPost(@ModelAttribute Post post, @AuthenticationPrincipal OAuth2User principal) {


    String memberName = principal.getAttribute("preferred_username"); // 또는 "name" 속성 사용

    Member member = memberRepository.findByMemberName(memberName);
    if (member == null) {
      throw new UsernameNotFoundException("Member not found");
    }

    post.setMemberId(member.getMemberId());

    postRepository.save(post);
    return "redirect:/postsonmember";
  }

}
