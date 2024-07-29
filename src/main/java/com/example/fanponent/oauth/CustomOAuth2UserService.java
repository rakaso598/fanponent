package com.example.fanponent.oauth;

import com.example.fanponent.oauth.OAuthAttributes;
import com.example.fanponent.config.SessionMember;
import com.example.fanponent.entity.Member;
import com.example.fanponent.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
    String name = (String) attributes.get("name");
    String email = (String) attributes.get("email");

    // 예시 데이터로 사용자 객체 생성
    CustomOAuth2User customUser = new CustomOAuth2User(name, email, attributes);

    return customUser;

    // 로그인한 사용자의 이름과 이메일을 CustomOAuth2User 객체에 저장
  }

  private Member saveOrUpdate(OAuthAttributes attributes) {
    Member member = memberRepository.findByEmail(attributes.getEmail())
        .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
        .orElse(attributes.toEntity());

    return memberRepository.save(member);
  }
}

