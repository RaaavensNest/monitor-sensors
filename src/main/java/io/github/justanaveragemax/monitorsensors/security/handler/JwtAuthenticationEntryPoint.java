package io.github.justanaveragemax.monitorsensors.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.justanaveragemax.monitorsensors.dto.resposnse.ApplicationErrorResponse;
import io.github.justanaveragemax.monitorsensors.util.ConstantsUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private final ObjectMapper objectMapper;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding(StandardCharsets.UTF_8.name());

    response.getWriter().write(objectMapper.writeValueAsString(
        new ApplicationErrorResponse(
            HttpStatus.UNAUTHORIZED.value(),
            authException.getMessage(),
            LocalDateTime.now(ConstantsUtil.DEFAULT_TIME_ZONE).toString()
        )
    ));
  }
}
