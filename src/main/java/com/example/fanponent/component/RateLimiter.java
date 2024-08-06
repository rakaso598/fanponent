//package com.example.fanponent.component;
//
//import org.springframework.stereotype.Component;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.TimeUnit;
//
//@Component
//public class RateLimiter {
//
//  private final ConcurrentHashMap<Long, Long> userLastRequestTime = new ConcurrentHashMap<>();
//
//  public boolean isAllowed(Long memberId) {
//    long currentTime = System.currentTimeMillis();
//    long lastRequestTime = userLastRequestTime.getOrDefault(memberId, 0L);
//
//    if (currentTime - lastRequestTime < TimeUnit.SECONDS.toMillis(5)) {
//      return false;
//    }
//
//    userLastRequestTime.put(memberId, currentTime);
//    return true;
//  }
//}
