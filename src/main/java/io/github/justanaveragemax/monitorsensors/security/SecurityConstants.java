package io.github.justanaveragemax.monitorsensors.security;

import java.util.Arrays;
import org.springframework.util.AntPathMatcher;

public class SecurityConstants {
  public static final String[] PUBLIC_ENDPOINTS = {
      "/swagger-ui.html",
      "/swagger-ui/**",
      "/v3/api-docs/**",
      "/v3/api-docs.yaml",
      "/swagger-resources",
      "/login",
      "/register"
  };

  public static final String AUTHORIZATION_HEADER = "Authorization";
  public static final String BEARER_PREFIX = "Bearer ";

  public static boolean isPublicEndpoint(String requestUri){
    final AntPathMatcher matcher = new AntPathMatcher();
    return Arrays.stream(PUBLIC_ENDPOINTS)
        .anyMatch(pattern -> matcher.match(pattern, requestUri));
  }

}
