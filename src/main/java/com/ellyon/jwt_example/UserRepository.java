package com.ellyon.jwt_example;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ellyon.jwt_example.controllers.User;

public interface UserRepository extends CrudRepository<User, String>{
    Optional<User> findByUsername(String username);
}
