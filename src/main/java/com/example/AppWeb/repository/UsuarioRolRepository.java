package com.example.AppWeb.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AppWeb.model.UsuarioRol;

public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Long> {
    List<UsuarioRol> findByUsuarioId(Long usuarioId);
}
