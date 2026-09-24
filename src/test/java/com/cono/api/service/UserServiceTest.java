package com.cono.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.cono.api.attachment.IntegrationTest;
import com.cono.api.model.User;
import com.cono.api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationTest
public class UserServiceTest {

  @Autowired UserService service;
  @Autowired UserRepository repository;

  @Test
  void shouldCreateUser() {
    User created =
        service.createUser("Carlos", "Perez", "carlitos.pe@example.com", "SegurePass123!");

    User persisted = repository.findById(created.getId()).orElseThrow();

    assertThat(persisted)
        .usingRecursiveComparison()
        .ignoringFields("created", "updated")
        .isEqualTo(created);
  }
}
