package com.example.AppWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.AppWeb.model.Contacto;

public interface ContactoRepository extends JpaRepository<Contacto, Integer> {
}
