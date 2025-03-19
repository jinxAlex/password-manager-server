package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

@Controller
@RequestMapping(path = "/demo")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String addNewUser(@RequestBody User user) {
        String response;
        if(userRepository.findByEmail(user.getEmail()) != null){
            response = "User already on database";
        }else{
            userRepository.save(user); // Usando el repositorio agrega el usuario que recibe por JSON, este usuario ya tiene la contraseña encriptada junto al email
            response = "Saved";
        }
        return response;
    }

    @PostMapping(path = "/checkPassword", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String checkPassword(@RequestBody User user) {
        // Busca al usuario por email en la base de datos
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser == null) {
            return "User not found";
        }
        // Compara la contraseña proporcionada con la almacenada
        if (existingUser.getPassword().equals(user.getPassword())) {
            return "User found with matching password";
        } else {
            return "Incorrect password";
        }
    }
    
}