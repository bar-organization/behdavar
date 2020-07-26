package com.bar.behdavarapplication.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthenticationService {

  private Map<String, String> authTokens;


  public AuthenticationService() {
    this.authTokens = new ConcurrentHashMap<>();
  }

  public String login(String identity) {
    String authToken = String.format("%s-%s", identity, UUID.randomUUID().toString());
    this.authTokens.put(authToken, identity);
    return authToken;
  }

}
