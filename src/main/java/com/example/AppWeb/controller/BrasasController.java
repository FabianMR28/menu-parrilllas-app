package com.example.AppWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.AppWeb.repository.ProductoRepository;

@Controller
public class BrasasController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/brasas")
    public String brasas(Model model) {
        model.addAttribute("brasas", productoRepository.findByCategoria("brasas"));
        return "brasas"; // templates/brasas.html
    }
}
