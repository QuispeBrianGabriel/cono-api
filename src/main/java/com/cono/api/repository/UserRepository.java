package com.cono.api.repository;

import java.util.UUID;

import org.springframework.data.repository.Repository;

import com.cono.api.model.User;

public interface UserRepository extends Repository<User, UUID> {
    User save(User user);
}
