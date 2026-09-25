package com.cono.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import com.cono.api.contracts.AuthResponse;
import com.cono.api.contracts.LoginRequest;
import com.cono.api.contracts.RegisterRequest;
import com.cono.api.model.User;
import com.cono.api.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
  private final AuthService serv;

  @PostMapping("/register")
  public ResponseEntity<EntityModel<AuthResponse>> register(
      @Valid @RequestBody RegisterRequest req) {
    User us = serv.register(req.name(), req.surname(), req.email(), req.password());
    AuthResponse resp = AuthResponse.of(us);
    Link self = linkTo(UserController.class).slash(us.getId()).withSelfRel();
    var model = EntityModel.of(resp, self);
    return ResponseEntity.created(self.toUri()).body(model);
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req) {
    User user = serv.authenticate(req.email(), req.password());
    return ResponseEntity.ok(AuthResponse.of(user));
  }
}
