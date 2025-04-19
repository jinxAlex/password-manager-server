package com.example.demo.controllers;

import java.security.Principal;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Credential;
import com.example.demo.entities.User;
import com.example.demo.repositories.CredentialRepository;
import com.example.demo.repositories.UserRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping(path = "/credentials")
public class CredentialController {

    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public @ResponseBody String addNewCredential(@RequestBody Credential credentialRequest, Principal principal) {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        if (user == null) {
            return "User not found";
        }

        Credential newCredential = new Credential();
        newCredential.setUser(user);
        newCredential.setEncryptedData(credentialRequest.getEncryptedData());
        newCredential.setSalt(credentialRequest.getSalt());
        System.out.println(credentialRequest.getSalt());
        credentialRepository.save(newCredential);
        
        return "Credential saved";
    }

    @PostMapping(path = "/getAll", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<Credential> getAllCredentials(@RequestBody User userRequest) {
        User user = userRepository.findByEmail(userRequest.getEmail()).get();
        if (user == null) {
            return Collections.emptyList();
        }
        
        return credentialRepository.findByUser(user);
    }
}