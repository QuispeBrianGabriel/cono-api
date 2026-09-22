package com.cono.api.service;

import com.cono.api.model.User;
import com.cono.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public User createUser(String name, String surname, String email, String password) {
    User user = User.builder().name(name).surname(surname).email(email).password(password).build();
    return userRepository.save(user);
  }
}
