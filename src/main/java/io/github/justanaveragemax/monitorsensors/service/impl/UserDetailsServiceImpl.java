package io.github.justanaveragemax.monitorsensors.service.impl;

import io.github.justanaveragemax.monitorsensors.repository.UserRepository;
import io.github.justanaveragemax.monitorsensors.util.ExceptionMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(@NonNull final String username) throws UsernameNotFoundException {
    log.info("Loading user by username '{}'", username);

    final UserDetails user = userRepository.findByEmail(username).orElseThrow(() -> {
      final String exceptionMessage = ExceptionMessage.USERNAME_NOT_FOUND.format(username);
      log.error(exceptionMessage);
      return new UsernameNotFoundException(exceptionMessage);
    });

    log.info("Found user with username '{}'", username);
    return user;
  }
}
