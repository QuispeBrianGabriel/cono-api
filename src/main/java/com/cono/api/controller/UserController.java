package com.cono.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import com.cono.api.contracts.UserRequest;
import com.cono.api.contracts.UserResponse;
import com.cono.api.model.User;
import com.cono.api.service.UserService;
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
@RequestMapping("/users")
public class UserController {
  private final UserService serv;

  @PostMapping
  public ResponseEntity<EntityModel<UserResponse>> createUser(@Valid @RequestBody UserRequest req) {
    User us = serv.createUser(req.name(), req.surname(), req.email(), req.password());
    UserResponse resp = UserResponse.from(us);
    Link self = linkTo(UserController.class).slash(us.getId()).withSelfRel();
    var model = EntityModel.of(resp, self);
    return ResponseEntity.created(self.toUri()).body(model);
  }
}
