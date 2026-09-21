package com.cono.api.contracts;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(

    @NotBlank
    String name,

    @NotBlank
    String surname,

    @NotBlank
    @Email
    String email,

    @NotBlank
    String password

) {}
