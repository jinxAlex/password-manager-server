package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

/**
 * Controlador REST para operaciones relacionadas con usuarios.
 * Implementa UserDetailsService para la autenticación.
 */
@RestController
@RequestMapping("/user")
public class UserController implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Registra un nuevo usuario si el correo no está en uso.
     * @param user el usuario a registrar
     * @return respuesta HTTP indicando el resultado
     */
    @PostMapping(path = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addNewUser(@RequestBody User user) {

        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body("User already in database");
        } else {
            userRepository.save(user);
            return ResponseEntity.ok("User created");
        }
    }

    /**
     * Verifica las credenciales de un usuario para login.
     * @param user el usuario con email y contraseña a verificar
     * @return respuesta HTTP indicando si la autenticación fue exitosa
     */
    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> checkPassword(@RequestBody User user) {

        Optional<User> existingUserOpt = userRepository.findByEmail(user.getEmail());

        if (!existingUserOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User existingUser = existingUserOpt.get();

        if(existingUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok("User found with matching password");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
        }
    }

    /**
     * Carga los detalles del usuario por su email.
     * @param email el email del usuario
     * @return los detalles del usuario para Spring Security
     * @throws UsernameNotFoundException si el usuario no existe
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User usuario = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getPassword(),
                new ArrayList<>()
        );
    }
}
