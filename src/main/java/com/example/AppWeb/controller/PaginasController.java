package com.example.AppWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.AppWeb.repository.ProductoRepository;

@Controller
public class PaginasController {

	@Autowired
	private ProductoRepository productoRepository;
	
    @GetMapping("/bebidas")
    public String bebidas(Model model) {
        model.addAttribute("bebidas", productoRepository.findByCategoria("bebidas"));
        return "bebidas"; // templates/bebidas.html
    }

    @GetMapping("/parrillas")
    public String parrillas(Model model) {
        model.addAttribute("parrillas", productoRepository.findByCategoria("parrillas"));
        return "parrillas"; // templates/parrillas.html
    }
    
    @GetMapping("/extras")
    public String extras(Model model) {
        model.addAttribute("extras", productoRepository.findByCategoria("extras"));
        return "extras"; // templates/extras.html
    }
    
    @GetMapping("/especiales")
    public String especiales(Model model) {
        model.addAttribute("especiales", productoRepository.findByCategoria("especiales"));
        return "especiales"; // templates/especiales.html
    }
    
    @GetMapping("/brasas")
    public String brasas(Model model) {
        model.addAttribute("brasas", productoRepository.findByCategoria("brasas"));
        return "brasas"; // templates/brasas.html
    }
}
