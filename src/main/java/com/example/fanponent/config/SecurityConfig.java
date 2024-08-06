package com.example.fanponent.config;

import com.example.fanponent.oauth.CustomOAuth2AuthenticationSuccessHandler;
import com.example.fanponent.oauth.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@EnableWebSecurity
public class SecurityConfig {

  private final CustomOAuth2UserService customOAuth2UserService;
  private final CustomOAuth2AuthenticationSuccessHandler customOAuth2AuthenticationSuccessHandler;

  @Autowired
  public SecurityConfig(CustomOAuth2UserService customOAuth2UserService, CustomOAuth2AuthenticationSuccessHandler customOAuth2AuthenticationSuccessHandler) {
    this.customOAuth2UserService = customOAuth2UserService;
    this.customOAuth2AuthenticationSuccessHandler = customOAuth2AuthenticationSuccessHandler;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
    requestCache.setMatchingRequestParameterName(null);
    http
        .csrf(csrf -> csrf.disable())
        .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
            .anyRequest().authenticated()
        )
        .logout(logout -> logout.logoutSuccessUrl("/"))
        .oauth2Login(oauth2 -> oauth2
            .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
            .defaultSuccessUrl("/main", true)
        )
        // requestCache null
        .requestCache((cache) -> cache
            .requestCache(requestCache)
        );
    return http.build();
  }


}

