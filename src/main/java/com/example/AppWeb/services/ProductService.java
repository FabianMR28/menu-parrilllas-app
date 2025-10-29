package com.example.AppWeb.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.AppWeb.model.Producto;

@Service
public class ProductService {
	
    private List<Producto> productos = new ArrayList<>(Arrays.asList(
        // Parrillas
        new Producto(1L, "producto.bife.nombre", "producto.bife.descripcion", 55.0, "/img/parrillas/bife.webp", "parrillas"),
        new Producto(2L, "producto.costillas.nombre", "producto.costillas.descripcion", 65.0, "/img/parrillas/costillas.avif", "parrillas"),
        new Producto(3L, "producto.chorizo.nombre", "producto.chorizo.descripcion", 25.0, "/img/parrillas/chorizo.jpeg", "parrillas"),
        new Producto(4L, "producto.asado.nombre", "producto.asado.descripcion", 70.0, "/img/parrillas/asado.jpg", "parrillas"),

        // Bebidas
        new Producto(5L, "producto.coca.nombre", "producto.coca.descripcion", 10.0, "/img/bebidas/Coca.png", "bebidas"),
        new Producto(6L, "producto.cerveza.nombre", "producto.cerveza.descripcion", 65.0, "/img/bebidas/Cerveza.png", "bebidas"),
        new Producto(7L, "producto.chicha.nombre", "producto.chicha.descripcion", 8.0, "/img/bebidas/Chicha.png", "bebidas"),
        new Producto(8L, "producto.limonada.nombre", "producto.limonada.descripcion", 12.0, "/img/bebidas/Limonada.png", "bebidas"),

        // Brasas
        new Producto(9L, "producto.polloentero.nombre", "producto.polloentero.descripcion", 45.0, "/img/brasas/pollo-entero.avif", "brasas"),
        new Producto(10L, "producto.mediopollo.nombre", "producto.mediopollo.descripcion", 25.0, "/img/brasas/medio-pollo.jpeg", "brasas"),
        new Producto(11L, "producto.cuartopollo.nombre", "producto.cuartopollo.descripcion", 15.0, "/img/brasas/cuarto-pollo.jpg", "brasas"),

        // Extras
        new Producto(12L, "producto.huancaina.nombre", "producto.huancaina.descripcion", 12.0, "/img/extras/huancaina.webp", "extras"),
        new Producto(13L, "producto.anticuchos.nombre", "producto.anticuchos.descripcion", 18.0, "/img/extras/anticuchos.jpg", "extras"),
        new Producto(14L, "producto.tequenos.nombre", "producto.tequenos.descripcion", 15.0, "/img/extras/tequenos.webp", "extras"),
        new Producto(15L, "producto.ensalada.nombre", "producto.ensalada.descripcion", 10.0, "/img/extras/ensalada.webp", "extras"),

        // Especiales
        new Producto(20L, "producto.lomo.nombre", "producto.lomo.descripcion", 28.0, "/img/especiales/lomo.jpg", "especiales"),
        new Producto(21L, "producto.aji.nombre", "producto.aji.descripcion", 25.0, "/img/especiales/aji.webp", "especiales"),
        new Producto(22L, "producto.arrozpollo.nombre", "producto.arrozpollo.descripcion", 22.0, "/img/especiales/arroz-pollo.png", "especiales"),
        new Producto(23L, "producto.seco.nombre", "producto.seco.descripcion", 30.0, "/img/especiales/seco.webp", "especiales")
    ));

    public List<Producto> listarPorCategoria(String categoria) {
        return productos.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                .toList();
    }

    public List<Producto> listarTodos() {
        return productos;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void eliminarProducto(Long id) {
        productos.removeIf(p -> p.getId().equals(id));
    }

    public void actualizarProducto(Producto productoActualizado) {
        productos.stream()
                .filter(p -> p.getId().equals(productoActualizado.getId()))
                .findFirst()
                .ifPresent(p -> {
                    p.setNombre(productoActualizado.getNombre());
                    p.setDescripcion(productoActualizado.getDescripcion());
                    p.setPrecio(productoActualizado.getPrecio());
                    p.setImagen(productoActualizado.getImagen());
                    p.setCategoria(productoActualizado.getCategoria());
                });
    }
}
