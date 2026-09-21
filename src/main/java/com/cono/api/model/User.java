package com.cono.api.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import org.springframework.data.relational.core.mapping.Table;

@Table("users")
@Getter
@SuperBuilder
public class User extends Entity {

    private String name;
    private String surname;
    private String email;
    private String password;

}