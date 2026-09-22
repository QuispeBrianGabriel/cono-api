package com.cono.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cono.api.contracts.UserResponse;
import com.cono.api.EndToEndTest;
import com.cono.api.contracts.UserRequest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserControllerTest extends EndToEndTest {

  @Test 
  @DisplayName("POST /users")
  void shouldCreateUser() {
    
    var request = new UserRequest(
      "Carlos",
      "Perez",
      "carlitos.pe@example.com",
      "SegurePass123!"
    );

    var expected = new UserResponse(
      null,
      "Carlos",
      "Perez",
      "carlitos.pe@example.com"
    );

    client.post()   
      .uri("/users")
      .body(request)
      .exchange()
      .expectStatus().isCreated()
      .expectHeader().valueMatches("Location", ".*/users/.*")
      .expectBody(UserResponse.class)
      .value(actual -> assertThat(actual)
        .usingRecursiveComparison()
        .ignoringFields("id")
        .isEqualTo(expected)
      );
  }
}
