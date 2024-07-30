package com.example.fanponent.oauth;

import com.example.fanponent.config.SessionMember;
import com.example.fanponent.entity.Member;
import com.example.fanponent.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
  private final MemberRepository memberRepository;
  private final HttpSession httpSession;

  @Autowired
  public CustomOAuth2UserService(MemberRepository memberRepository, HttpSession httpSession) {
    this.memberRepository = memberRepository;
    this.httpSession = httpSession;
  }

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);

    // 사용자 정보 처리 로직
    String registrationId = userRequest.getClientRegistration().getRegistrationId();
    String userNameAttributeName = userRequest.getClientRegistration()
        .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

    Map<String, Object> attributes = oAuth2User.getAttributes();

    // OAuthAttributes 객체 생성
    OAuthAttributes oAuthAttributes = OAuthAttributes.of(registrationId, userNameAttributeName, attributes);

    // 멤버 정보 저장 또는 업데이트
    Member member = saveOrUpdate(oAuthAttributes);

    // 세션에 멤버 정보 저장
    httpSession.setAttribute("member", new SessionMember(member));

    // 예시 데이터로 사용자 객체 생성
    CustomOAuth2User customUser = new CustomOAuth2User(oAuthAttributes.getName(), oAuthAttributes.getEmail(), attributes);

    return customUser;
  }

  private Member saveOrUpdate(OAuthAttributes attributes) {
    Member member = memberRepository.findByEmail(attributes.getEmail())
        .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
        .orElse(attributes.toEntity());

    // 비밀번호를 사용하지 않도록 설정
    member.setPassword("default_password"); // 기본 비밀번호 설정

    return memberRepository.save(member);
  }
}



