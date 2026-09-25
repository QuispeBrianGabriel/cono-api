package com.cono.api.contracts;

import com.cono.api.model.User;
import java.util.UUID;

public record AuthResponse(UUID id, String name, String surname, String email) {
  public static AuthResponse of(User user) {
    return new AuthResponse(user.getId(), user.getName(), user.getSurname(), user.getEmail());
  }
}
