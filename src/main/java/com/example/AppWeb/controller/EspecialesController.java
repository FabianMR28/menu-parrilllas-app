package com.example.AppWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.AppWeb.repository.ProductoRepository;

@Controller
public class EspecialesController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/especiales")
    public String especiales(Model model) {
        model.addAttribute("especiales", productoRepository.findByCategoria("especiales"));
        return "especiales"; // templates/especiales.html
    }
}
