package com.cono.api.controller;

import com.cono.api.contracts.UserRequest;
import com.cono.api.contracts.UserResponse;
import com.cono.api.model.User;
import com.cono.api.service.UserService;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
  private final UserService serv;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest req) {
    User user = serv.createUser(req.name(), req.surname(), req.email(), req.password());
    URI location = URI.create("/users/" + user.getId());
    UserResponse res =
        new UserResponse(user.getId(), user.getName(), user.getSurname(), user.getEmail());
    return ResponseEntity.created(location).body(res);
  }
}
