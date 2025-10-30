package com.example.AppWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.AppWeb.model.Producto;
import com.example.AppWeb.repository.ProductoRepository;

@Controller
public class AdminProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // Listar productos
    @GetMapping("/listaProductos")
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoRepository.findAll());
        return "lista_producto";
    }

    // Guardar o actualizar producto
    @PostMapping("/admin/productos/actualizar")
    public String actualizarProducto(@ModelAttribute Producto productoActualizado) {
        productoRepository.save(productoActualizado); // save() inserta o actualiza
        return "redirect:/listaProductos";
    }

    // Eliminar producto
    @PostMapping("/admin/productos/eliminar")
    public String eliminarProducto(@RequestParam("id") Integer id) {
        productoRepository.deleteById(id);
        return "redirect:/listaProductos";
    }
}
