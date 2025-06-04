package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entidad que representa un usuario del sistema.
 */
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  /**
   * Establece el identificador del usuario.
   * @param id el id a establecer
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Obtiene el identificador del usuario.
   * @return el id del usuario
   */
  public Integer getId() {
    return id;
  }

  /**
   * Establece el correo electrónico del usuario.
   * @param email el correo electrónico a establecer
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Obtiene el correo electrónico del usuario.
   * @return el correo electrónico
   */
  public String getEmail() {
    return email;
  }

  /**
   * Establece la contraseña del usuario.
   * @param password la contraseña a establecer
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Obtiene la contraseña del usuario.
   * @return la contraseña
   */
  public String getPassword() {
    return password;
  }
}
