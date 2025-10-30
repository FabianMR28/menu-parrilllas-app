package com.example.AppWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.AppWeb.repository.ProductoRepository;

@Controller
public class BebidasController {
	
	@Autowired
	private ProductoRepository productoRepository;
	
    @GetMapping("/bebidas")
    public String bebidas(Model model) {
        model.addAttribute("bebidas", productoRepository.findByCategoria("bebidas"));
        return "bebidas"; // templates/bebidas.html
    }
}
