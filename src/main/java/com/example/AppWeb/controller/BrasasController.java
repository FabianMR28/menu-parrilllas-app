package com.example.AppWeb.controller;

import com.example.AppWeb.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BrasasController {

	@Autowired
	private ProductService productService;
	
    @GetMapping("/brasas")
    public String brasas(Model model) {
        model.addAttribute("brasas", productService.listarPorCategoria("brasas"));
        return "brasas"; // templates/brasas.html
    }
}

