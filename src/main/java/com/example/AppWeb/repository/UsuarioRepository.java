package com.example.AppWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.AppWeb.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
    Usuario findByCorreo(String correo);
}
