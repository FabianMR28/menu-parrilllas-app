package com.example.AppWeb.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.AppWeb.model.Producto;
import com.example.AppWeb.repository.ProductoRepository;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Listar todos
    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("productos", productoRepository.findAll());
        return "lista_producto";
    }

    // Listar por categoría
    @GetMapping("/categoria")
    public String listarPorCategoria(@RequestParam String categoria, Model model) {
        model.addAttribute("productos", productoRepository.findByCategoria(categoria));
        return "lista_producto";
    }

    // Guardar / Registrar
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto) {
        productoRepository.save(producto);
        return "redirect:/productos";
    }

    // Eliminar
    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("id") Integer id) {
        productoRepository.deleteById(id);
        return "redirect:/productos";
    }

    // Actualizar
    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Producto producto) {
        productoRepository.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/menu")
    public String mostrarMenu(@RequestParam(value = "busqueda", required = false) String busqueda, Model model) {
        List<Producto> productos;

        if (busqueda != null && !busqueda.isEmpty()) {
            productos = productoRepository.findByNombreContainingIgnoreCaseOrCategoriaContainingIgnoreCase(busqueda,
                    busqueda);
        } else {
            productos = productoRepository.findAll();
        }

        model.addAttribute("productos", productos);
        model.addAttribute("busqueda", busqueda);

        return "menu";
    }

}
