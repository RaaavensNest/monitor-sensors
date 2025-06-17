package io.github.justanaveragemax.monitorsensors.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.justanaveragemax.monitorsensors.dto.resposnse.ApplicationErrorResponse;
import io.github.justanaveragemax.monitorsensors.service.JwtService;
import io.github.justanaveragemax.monitorsensors.util.ConstantsUtil;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;
  private final ObjectMapper objectMapper;

  @Override
  protected void doFilterInternal(@NonNull final HttpServletRequest request,
                                  @NonNull final HttpServletResponse response,
                                  @NonNull final FilterChain filterChain) throws ServletException, IOException {

    final String header = request.getHeader(SecurityConstants.AUTHORIZATION_HEADER);

    if (header == null
        || !header.startsWith(SecurityConstants.BEARER_PREFIX)
        || SecurityConstants.isPublicEndpoint(
        request.getRequestURI())) {
      filterChain.doFilter(request, response);
      return;
    }

    try {
      final String jwt = header.substring(SecurityConstants.BEARER_PREFIX.length());
      final String email = jwtService.extractEmail(jwt);

      if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        if (jwtService.isTokenValid(jwt, userDetails)) {
          UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
              userDetails,
              null,
              userDetails.getAuthorities()
          );

          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authToken);
        }
      }
      filterChain.doFilter(request, response);

    } catch (JwtException | AuthenticationException ex) {
      sendUnauthorizedErrorResponse(response, ex.getMessage());
    }
  }

  private void sendUnauthorizedErrorResponse(HttpServletResponse response, String errorDescription)
      throws IOException {
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

    final ApplicationErrorResponse errorRs = new ApplicationErrorResponse();

    errorRs.setCode(HttpStatus.UNAUTHORIZED.value());
    errorRs.setDescription(errorDescription);
    errorRs.setTimestamp(LocalDateTime.now(ConstantsUtil.DEFAULT_TIME_ZONE).toString());

    response.getWriter().write(objectMapper.writeValueAsString(errorRs));
  }
}
