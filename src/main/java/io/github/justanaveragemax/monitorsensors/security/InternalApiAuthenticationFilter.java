package io.github.justanaveragemax.monitorsensors.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class InternalApiAuthenticationFilter extends OncePerRequestFilter {

  @Value("${internal.api-key}")
  private String internalApiKey;

  @Override
  protected void doFilterInternal(@NonNull final HttpServletRequest request,
                                  @NonNull final HttpServletResponse response,
                                  @NonNull final FilterChain filterChain) throws ServletException, IOException {

    final String providedKey = request.getHeader(SecurityConstants.INTERNAL_API_HEADER);

    if (providedKey != null && providedKey.equals(internalApiKey)) {
      final List<GrantedAuthority> authorities = List.of(
          new SimpleGrantedAuthority(SecurityConstants.INTERNAL_AUTHORITY));

      final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
          SecurityConstants.INTERNAL_USERNAME,
          null,
          authorities
      );

      SecurityContextHolder.getContext().setAuthentication(auth);
    }

    filterChain.doFilter(request, response);

  }
}
