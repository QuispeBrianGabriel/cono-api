package com.cono.api.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.cono.api.model.User;

public interface UserRepository extends CrudRepository<User, UUID> {}
