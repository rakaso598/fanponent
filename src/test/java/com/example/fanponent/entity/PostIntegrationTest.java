package com.example.fanponent.entity;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PostIntegrationTest {

  @Autowired
  private EntityManager entityManager;

  private Post post;
  private Member member;

  @BeforeEach
  public void setUp() {
    // 기존 멤버 조회
    List<Member> existingMembers = entityManager.createQuery("SELECT m FROM Member m WHERE m.memberName = :memberName", Member.class)
        .setParameter("memberName", "nexon")
        .getResultList();

    if (existingMembers.isEmpty()) {
      member = new Member();
      member.setMemberId(15L); // memberId 설정
      member.setMemberName("nexon");
      member.setEmail("nexonaddress98@gmail.com1"); // email 설정
      member.setPassword("default_password1"); // password 설정
      member.setRole("USER"); // role 설정
      member.setProfilePictureUrl("https://lh3.googleusercontent.com/a/ACg8ocKYKbYaxuL4Sxlo-__I5iCyokRR7HGJge61zs7VDndhwLwELg=s96-c"); // profilePictureUrl 설정
      entityManager.persist(member);
    } else {
      member = existingMembers.get(0);
    }

    post = new Post();
    post.setPostTitle("testing1");
    post.setPostContent("테스트내용");
    post.setMember(member);
    post.setLikeCount(0);
    entityManager.persist(post);
    entityManager.flush();
  }


//  @BeforeEach
//  public void setUp() {
//    member = new Member();
//    member.setMemberId(9L);
//    member.setMemberName("nexon");
//    member.setEmail("nexonaddress98@gmail.com");
//    member.setPassword("default_password");
//    member.setRole("USER");
//    member.setProfilePictureUrl("https://lh3.googleusercontent.com/a/ACg8ocKYKbYaxuL4Sxlo-__I5iCyokRR7HGJge61zs7VDndhwLwELg=s96-c");
//    entityManager.merge(member);
//
//    post = new Post();
//    post.setPostTitle("testing1");
//    post.setPostContent("테스트내용");
//    post.setMember(member);
//    post.setLikeCount(0);
//    entityManager.merge(post);
//    entityManager.flush();
//  }

  @Test
  public void testGetMemberName_WhenMemberIsNotNull() {
    // When
    String memberName = post.getMemberName();

    // Then
    assertEquals("nexon", memberName);
    System.out.println("testGetMemberName_WhenMemberIsNotNull passed");
  }

  @Test
  public void testGetMemberName_WhenMemberIsNull() {
    // Given
    post.setMember(null);

    // When & Then
    assertThrows(IllegalStateException.class, () -> {
      post.getMemberName();
    });
    System.out.println("testGetMemberName_WhenMemberIsNull passed");
  }
}
