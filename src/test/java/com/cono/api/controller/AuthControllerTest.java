package com.cono.api.controller;

import com.cono.api.attachment.EndToEndTest;
import com.cono.api.contracts.LoginRequest;
import com.cono.api.contracts.RegisterRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.client.RestTestClient;

@EndToEndTest
public class AuthControllerTest {

  @Autowired private RestTestClient client;

  private static final String NAME = "Carlos";
  private static final String SURNAME = "Perez";
  private static final String EMAIL = "carlitos.pe@example.com";
  private static final String PASSWORD = "SegurePass123!";

  @Test
  void shouldRegister() {
    RegisterRequest register = new RegisterRequest(NAME, SURNAME, EMAIL, PASSWORD);
    client
        .post()
        .uri("/auth/register")
        .body(register)
        .exchange()
        .expectStatus()
        .isCreated()
        .expectHeader()
        .exists(HttpHeaders.LOCATION)
        .expectBody()
        .jsonPath("$.id")
        .isNotEmpty()
        .jsonPath("$.name")
        .isEqualTo(NAME)
        .jsonPath("$.surname")
        .isEqualTo(SURNAME)
        .jsonPath("$.email")
        .isEqualTo(EMAIL)
        .jsonPath("$._links.self.href")
        .isNotEmpty();
  }

  @Test
  void shouldLogin() {
    RegisterRequest register = new RegisterRequest(NAME, SURNAME, EMAIL, PASSWORD);
    client.post().uri("/auth/register").body(register).exchange();

    LoginRequest login = new LoginRequest(EMAIL, PASSWORD);
    client
        .post()
        .uri("/auth/login")
        .body(login)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("$.id")
        .isNotEmpty()
        .jsonPath("$.name")
        .isEqualTo(NAME)
        .jsonPath("$.surname")
        .isEqualTo(SURNAME)
        .jsonPath("$.email")
        .isEqualTo(EMAIL);
  }
}
