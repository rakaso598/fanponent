package com.example.fanponent.oauth;

import com.example.fanponent.entity.Member;
import com.example.fanponent.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  private MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String memberName) throws UsernameNotFoundException {
    Member member = memberRepository.findByMemberName(memberName);
    if (member == null) {
      throw new UsernameNotFoundException("Member not found");
    }
    return new CustomUserDetails(member);
  }
}

