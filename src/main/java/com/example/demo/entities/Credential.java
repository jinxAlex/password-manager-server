package com.example.demo.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

/**
 * Entidad que representa una credencial almacenada para un usuario.
 */
@Entity
@Table(name = "stored_credentials")
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @Column(nullable = false,length=500)
    private String encryptedData;

    @Column(nullable = false)
    private String iv;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    /**
     * Constructor por defecto.
     */
    public Credential() {
    }

    /**
     * Obtiene el identificador de la credencial.
     * @return el id de la credencial
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador de la credencial.
     * @param id el id a establecer
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el usuario asociado a la credencial.
     * @return el usuario
     */
    public User getUser() {
        return user;
    }

    /**
     * Establece el usuario asociado a la credencial.
     * @param user el usuario a establecer
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Obtiene el vector de inicialización (IV) usado para cifrar los datos.
     * @return el IV
     */
    public String getIv() {
        return iv;
    }

    /**
     * Establece el vector de inicialización (IV) usado para cifrar los datos.
     * @param iv el IV a establecer
     */
    public void setIv(String iv) {
        this.iv = iv;
    }

    /**
     * Obtiene los datos cifrados de la credencial.
     * @return los datos cifrados
     */
    public String getEncryptedData() {
        return encryptedData;
    }

    /**
     * Establece los datos cifrados de la credencial.
     * @param encryptedData los datos cifrados a establecer
     */
    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    /**
     * Obtiene la fecha y hora de creación de la credencial.
     * @return la fecha de creación
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

