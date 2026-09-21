package com.cono.api.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cono.api.contracts.UserRequest;
import com.cono.api.contracts.UserResponse;
import com.cono.api.model.User;
import com.cono.api.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request) {
        User user = userService.createUser(request.name(), request.surname(), request.email(), request.password());
        URI location = URI.create("/users/" + user.getId());
        UserResponse response = new UserResponse(user.getId(), user.getName(), user.getSurname(), user.getEmail());
        return ResponseEntity.created(location).body(response);
    }
}
