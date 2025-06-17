package io.github.justanaveragemax.monitorsensors.controller.impl;

import io.github.justanaveragemax.monitorsensors.controller.AuthenticationApi;
import io.github.justanaveragemax.monitorsensors.dto.request.AuthenticationRequest;
import io.github.justanaveragemax.monitorsensors.dto.resposnse.AuthenticationResponse;
import io.github.justanaveragemax.monitorsensors.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationApi {

  private final AuthenticationService authenticationService;

  @Override
  public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest request) {
    return ResponseEntity.ok(authenticationService.login(request));
  }

}
