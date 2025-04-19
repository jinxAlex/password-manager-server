package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    
    Optional<User> findByEmail(String email);

    User findByPassword(String password);   
}