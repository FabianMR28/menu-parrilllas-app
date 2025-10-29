package com.example.AppWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.AppWeb.services.ProductService;

@Controller
public class BebidasController {
	
	@Autowired
	private ProductService productService;
	
    @GetMapping("/bebidas")
    public String bebidas(Model model) {
        model.addAttribute("bebidas", productService.listarPorCategoria("bebidas"));
        return "bebidas"; // templates/bebidas.html
    }
}
