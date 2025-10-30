package com.example.AppWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.AppWeb.repository.ProductoRepository;

@Controller
public class ParrillasController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/parrillas")
    public String parrillas(Model model) {
        model.addAttribute("parrillas", productoRepository.findByCategoria("parrillas"));
        return "parrillas"; // templates/parrillas.html
    }
}
