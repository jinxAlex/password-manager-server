package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.User;

/**
 * Repositorio para operaciones CRUD sobre la entidad User.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    
    /**
     * Busca un usuario por su email.
     * @param email el email a buscar
     * @return un Optional con el usuario si existe
     */
    Optional<User> findByEmail(String email);

    /**
     * Busca un usuario por su contraseña.
     * @param password la contraseña a buscar
     * @return el usuario encontrado o null si no existe
     */
    User findByPassword(String password);   
}