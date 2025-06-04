package com.example.demo.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.Credential;
import com.example.demo.entities.User;
import java.util.List;

/**
 * Repositorio para operaciones CRUD sobre la entidad Credential.
 */
public interface CredentialRepository extends CrudRepository<Credential, Integer> {
    /**
     * Busca todas las credenciales asociadas a un usuario.
     * @param user el usuario
     * @return lista de credenciales del usuario
     */
    List<Credential> findByUser(User user);

    /**
     * Elimina todas las credenciales asociadas a un usuario.
     * @param user el usuario
     */
    @Modifying
    void deleteByUser(User user);
}