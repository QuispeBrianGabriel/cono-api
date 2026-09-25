package com.cono.api.service;

import com.cono.api.model.User;
import com.cono.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public User register(String name, String surname, String email, String password) {
    String hashed = passwordEncoder.encode(password);
    User user = User.builder().name(name).surname(surname).email(email).password(hashed).build();
    return userRepository.save(user);
  }

  public User authenticate(String email, String password) {
    User user =
        userRepository
            .findByEmail(email)
            .orElseThrow(() -> new BadCredentialsException("Credenciales invalidas"));
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new BadCredentialsException("Credenciales inválidas");
    }
    return user;
  }
}
