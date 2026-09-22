package com.cono.api.contracts;

import java.util.UUID;

public record UserResponse(UUID id, String name, String surname, String email) {}
