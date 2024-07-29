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

    String registrationId = userRequest.getClientRegistration().getRegistrationId();
    String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

    OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

    Member member = saveOrUpdate(attributes);
    httpSession.setAttribute("member", new SessionMember(member));

    return new DefaultOAuth2User(
        Collections.singleton(new SimpleGrantedAuthority(member.getRoleKey())),
        attributes.getAttributes(),
        attributes.getNameAttributeKey());
  }

  private Member saveOrUpdate(OAuthAttributes attributes) {
    Member member = memberRepository.findByEmail(attributes.getEmail())
        .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
        .orElse(attributes.toEntity());

    return memberRepository.save(member);
  }
}

