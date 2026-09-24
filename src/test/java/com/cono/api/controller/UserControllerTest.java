package com.cono.api.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.cono.api.attachment.EndToEndTest;
import com.cono.api.contracts.UserRequest;
import com.cono.api.contracts.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.client.RestTestClient;

@EndToEndTest
public class UserControllerTest {

  @Autowired private RestTestClient client;

  @Test
  @DisplayName("POST /users")
  void shouldCreateUser() {

    var request = new UserRequest("Carlos", "Perez", "carlitos.pe@example.com", "SegurePass123!");

    var expected = new UserResponse(null, "Carlos", "Perez", "carlitos.pe@example.com");

    client
        .post()
        .uri("/users")
        .body(request)
        .exchange()
        .expectStatus()
        .isCreated()
        .expectHeader()
        .valueMatches("Location", ".*/users/.*")
        .expectBody(UserResponse.class)
        .value(
            actual ->
                assertThat(actual)
                    .usingRecursiveComparison()
                    .ignoringFields("id")
                    .isEqualTo(expected));
  }
}
