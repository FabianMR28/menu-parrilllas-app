package com.example.AppWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.AppWeb.model.Producto;
import com.example.AppWeb.services.ProductService;

@Controller
public class ProductoController {

	@Autowired
    private ProductService productService;

    // Listar productos
    @GetMapping("/listaProductos")
    public String listarProductos(Model model) {
        model.addAttribute("productos", productService.listarTodos());
        return "lista_producto"; // HTML de listado de productos
    }

    // Actualizar producto
    @PostMapping("/admin/productos/actualizar")
    public String actualizarProducto(@ModelAttribute Producto productoActualizado) {
        productService.actualizarProducto(productoActualizado);
        return "redirect:/listaProductos";
    }

    @PostMapping("/admin/productos/eliminar")
    public String eliminarProducto(@RequestParam("id") Long id) {
        productService.eliminarProducto(id);
        return "redirect:/listaProductos";
    }
    
}
