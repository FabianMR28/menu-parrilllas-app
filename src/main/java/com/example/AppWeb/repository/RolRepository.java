package com.example.AppWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.AppWeb.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol findByNombre(String nombre);
}
