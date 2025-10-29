package com.example.AppWeb.controller;

import com.example.AppWeb.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExtrasController {

	@Autowired
	private ProductService productService;
	
    @GetMapping("/extras")
    public String extras(Model model) {
        model.addAttribute("extras", productService.listarPorCategoria("extras"));
        return "extras"; // templates/extras.html
    }
}

