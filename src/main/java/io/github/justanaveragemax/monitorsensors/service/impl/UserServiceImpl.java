package io.github.justanaveragemax.monitorsensors.service.impl;

import io.github.justanaveragemax.monitorsensors.entity.User;
import io.github.justanaveragemax.monitorsensors.repository.UserRepository;
import io.github.justanaveragemax.monitorsensors.service.UserService;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public Optional<User> findByEmail(@NonNull final String email) {
    return userRepository.findByEmail(email);
  }
}
