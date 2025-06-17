package io.github.justanaveragemax.monitorsensors.controller;

import io.github.justanaveragemax.monitorsensors.dto.request.AuthenticationRequest;
import io.github.justanaveragemax.monitorsensors.dto.request.RegistrationRequest;
import io.github.justanaveragemax.monitorsensors.dto.resposnse.AuthenticationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Tag(name = "Authentication", description = "Endpoints for authentication")
public interface AuthenticationApi {

  @PostMapping("/login")
  @Operation(
      summary = "Authenticate user",
      description = "Authenticate user with provided credentials"
  )
  ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request);


}
