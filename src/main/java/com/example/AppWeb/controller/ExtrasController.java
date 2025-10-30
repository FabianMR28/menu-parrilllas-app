package com.example.AppWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.AppWeb.repository.ProductoRepository;

@Controller
public class ExtrasController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/extras")
    public String extras(Model model) {
        model.addAttribute("extras", productoRepository.findByCategoria("extras"));
        return "extras"; // templates/extras.html
    }
}
