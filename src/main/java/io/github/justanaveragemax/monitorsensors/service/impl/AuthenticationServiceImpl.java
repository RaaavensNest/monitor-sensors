package io.github.justanaveragemax.monitorsensors.service.impl;

import io.github.justanaveragemax.monitorsensors.dto.request.AuthenticationRequest;
import io.github.justanaveragemax.monitorsensors.dto.resposnse.AuthenticationResponse;
import io.github.justanaveragemax.monitorsensors.entity.User;
import io.github.justanaveragemax.monitorsensors.service.AuthenticationService;
import io.github.justanaveragemax.monitorsensors.service.JwtService;
import io.github.justanaveragemax.monitorsensors.service.UserService;
import io.github.justanaveragemax.monitorsensors.util.ExceptionMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final UserService userService;

  @Override
  @Transactional
  public AuthenticationResponse login(@NonNull final AuthenticationRequest request) {

    final User user = userService.findByEmail(request.getEmail()).orElseThrow(() -> {
      final String exceptionMessage = ExceptionMessage.USERNAME_NOT_FOUND.format(request.getEmail());
      log.error(exceptionMessage);
      return new BadCredentialsException(ExceptionMessage.INVALID_CREDENTIALS_PROVIDED.getMessage());
    });

    if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      log.error(ExceptionMessage.INVALID_CREDENTIALS_PROVIDED.getMessage());
      throw new BadCredentialsException(ExceptionMessage.INVALID_CREDENTIALS_PROVIDED.getMessage());
    }

    final String accessToken = jwtService.generateAccessToken(user);
    final String refreshToken = jwtService.generateRefreshToken(user);

    return AuthenticationResponse.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }

}
