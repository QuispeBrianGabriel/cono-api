package com.cono.api.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.cono.api.attachment.EndToEndTest;
import com.cono.api.contracts.UserRequest;
import com.cono.api.contracts.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.test.web.servlet.client.RestTestClient.ResponseSpec;

@EndToEndTest
public class UserControllerTest {

  @Autowired private RestTestClient client;

  private static final String NAME = "Carlos";
  private static final String SURNAME = "Perez";
  private static final String EMAIL = "carlitos.pe@example.com";
  private static final String PASSWORD = "SegurePass123!";

  @Test
  @DisplayName("POST /users")
  void shouldCreateUser() {
    UserRequest request = new UserRequest(NAME, SURNAME, EMAIL, PASSWORD);
    ResponseSpec result = client.post().uri("/users").body(request).exchange();
    result
        .expectStatus()
        .isCreated()
        .expectHeader()
        .valueMatches(HttpHeaders.LOCATION, ".*/users/.*");

    UserResponse response = result.expectBody(UserResponse.class).returnResult().getResponseBody();
    assertThat(response)
        .isNotNull()
        .returns(NAME, r -> r.name())
        .returns(SURNAME, r -> r.surname())
        .returns(EMAIL, r -> r.email())
        .extracting(r -> r.id())
        .isNotNull();
  }
}
