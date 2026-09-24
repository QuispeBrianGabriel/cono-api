package com.cono.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.cono.api.attachment.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationTest
public class UserServiceTest {

  @Autowired UserService service;

  private static final String NAME = "Carlos";
  private static final String SURNAME = "Perez";
  private static final String EMAIL = "carlitos.pe@example.com";
  private static final String PASSWORD = "SegurePass123!";

  @Test
  void shouldCreateUser() {
    assertThat(service.createUser(NAME, SURNAME, EMAIL, PASSWORD))
        .isNotNull()
        .returns(NAME, u -> u.getName())
        .returns(SURNAME, u -> u.getSurname())
        .returns(EMAIL, u -> u.getEmail())
        .extracting(u -> u.getId())
        .isNotNull();
  }
}
