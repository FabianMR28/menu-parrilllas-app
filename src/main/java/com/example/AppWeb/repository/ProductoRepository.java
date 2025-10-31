package com.example.AppWeb.repository;

import com.example.AppWeb.model.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByCategoria(String categoria);

    List<Producto> findByNombreContainingIgnoreCaseOrCategoriaContainingIgnoreCase(String nombre, String categoria);

    // 🔍 Buscar por ID (en forma de lista)
    List<Producto> findByIdEquals(Integer id);

    // 💰 Buscar productos por precio (menor o igual)
    List<Producto> findByPrecioLessThanEqual(double precio);

    // 💰 Buscar productos por precio (mayor o igual)
    List<Producto> findByPrecioGreaterThanEqual(double precio);
}
