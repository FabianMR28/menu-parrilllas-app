package com.example.AppWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.AppWeb.model.Producto;
import com.example.AppWeb.services.ProductService;

public class AdminProductoController {
	@Autowired
    private ProductService productService;

    // Listar productos
    @GetMapping("/listaProductos")
    public String listarProductos(Model model) {
        model.addAttribute("productos", productService.listarTodos());
        return "lista_producto"; // HTML de listado de productos
    }

    // Actualizar producto
    @PostMapping("/actualizar")
    public String actualizarProducto(@ModelAttribute Producto productoActualizado) {
        // Como ProductService tiene lista fija, simulamos la actualización:
        productService.listarTodos().stream()
                .filter(p -> p.getId().equals(productoActualizado.getId()))
                .findFirst()
                .ifPresent(p -> {
                    p.setNombre(productoActualizado.getNombre());
                    p.setDescripcion(productoActualizado.getDescripcion());
                    p.setPrecio(productoActualizado.getPrecio());
                    p.setImagen(productoActualizado.getImagen());
                    p.setCategoria(productoActualizado.getCategoria());
                });
        return "redirect:/listaProductos";
    }

    // Eliminar producto
    @PostMapping("/eliminar")
    public String eliminarProducto(@RequestParam("id") Long id) {
        // Como la lista es inmutable (Arrays.asList), aquí solo simulamos la acción
        System.out.println("Eliminar producto con id: " + id + " (simulado)");
        return "redirect:/listaProductos";
    }
}
