package com.example.demo.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entities.Credential;
import com.example.demo.entities.User;
import com.example.demo.repositories.CredentialRepository;
import com.example.demo.repositories.UserRepository;

@Controller
@RequestMapping(path = "/credentials")
public class CredentialController {

    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String addNewCredential(@RequestBody Credential credentialRequest) {
        // Buscar el usuario por email
        User user = userRepository.findByEmail(credentialRequest.getUser().getEmail());
        
        if (user == null) {
            return "User not found";
        }
        
        // Crear y guardar la nueva credencial
        Credential newCredential = new Credential();
        newCredential.setUser(user);
        newCredential.setEncryptedData(credentialRequest.getEncryptedData());
        newCredential.setSalt(credentialRequest.getSalt());
        credentialRepository.save(newCredential);
        
        return "Credential saved";
    }

    @PostMapping(path = "/getAll", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<Credential> getAllCredentials(@RequestBody User userRequest) {
        // Verificar que el usuario existe
        User user = userRepository.findByEmail(userRequest.getEmail());
        if (user == null) {
            return Collections.emptyList();
        }
        
        // Obtener todas las credenciales del usuario
        return credentialRepository.findByUser(user);
    }
}