package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.Customizer;

/**
 * Configuración de seguridad para la aplicación.
 * Define las reglas de seguridad HTTP, el gestor de autenticación y el codificador de contraseñas.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configura la cadena de filtros de seguridad.
     *
     * @param http el objeto HttpSecurity para configurar las reglas de seguridad
     * @return la cadena de filtros de seguridad configurada
     * @throws Exception si ocurre un error durante la configuración
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/user/login", "/user/signup").permitAll() // Public endpoints
                        .requestMatchers("/credentials/**").authenticated() // Authentication required endpoints
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    /**
     * Proporciona el AuthenticationManager a partir de la configuración de autenticación.
     *
     * @param authConfig la configuración de autenticación
     * @return el AuthenticationManager configurado
     * @throws Exception si ocurre un error al obtener el AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Proporciona un PasswordEncoder que no realiza codificación (solo para propósitos de desarrollo).
     *
     * @return una implementación de PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.toString().equals(encodedPassword);
            }
        };
    }
}