package io.github.justanaveragemax.monitorsensors.service;

import io.github.justanaveragemax.monitorsensors.dto.request.AuthenticationRequest;
import io.github.justanaveragemax.monitorsensors.dto.resposnse.AuthenticationResponse;

public interface AuthenticationService {

  AuthenticationResponse login(AuthenticationRequest request);

}
