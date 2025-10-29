package com.example.AppWeb.controller;

import com.example.AppWeb.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ParrillasController {

	@Autowired
	private ProductService productService;
	
    @GetMapping("/parrillas")
    public String parrillas(Model model) {
        model.addAttribute("parrillas", productService.listarPorCategoria("parrillas"));
        return "parrillas"; // templates/parrillas.html
    }
}
