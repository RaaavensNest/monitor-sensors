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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

  private final ObjectMapper objectMapper;

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
                     AccessDeniedException accessDeniedException) throws IOException {
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding(StandardCharsets.UTF_8.name());

    response.getWriter().write(objectMapper.writeValueAsString(
        new ApplicationErrorResponse(
            HttpStatus.FORBIDDEN.value(),
            accessDeniedException.getMessage(),
            LocalDateTime.now(ConstantsUtil.DEFAULT_TIME_ZONE).toString()
        )
    ));
  }
}

