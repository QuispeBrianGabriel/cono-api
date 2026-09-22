package com.cono.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import org.springframework.data.relational.core.mapping.Table;

@Table("users")
@Getter
@SuperBuilder
@NoArgsConstructor
public class User extends Entity {

    private String name;
    private String surname;
    private String email;
    private String password;

}