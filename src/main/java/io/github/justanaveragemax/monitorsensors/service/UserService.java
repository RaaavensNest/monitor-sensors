package io.github.justanaveragemax.monitorsensors.service;

import io.github.justanaveragemax.monitorsensors.entity.User;
import java.util.Optional;

public interface UserService {

  Optional<User> findByEmail(String email);

}
