package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.Credential;
import com.example.demo.entities.User;
import java.util.List;

public interface CredentialRepository extends CrudRepository<Credential, Integer> {
    List<Credential> findByUser(User user);
}