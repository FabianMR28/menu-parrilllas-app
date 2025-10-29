package com.example.AppWeb.controller;

import com.example.AppWeb.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EspecialesController {

	@Autowired
	private ProductService productService;
	
    @GetMapping("/especiales")
    public String especiales(Model model) {
        model.addAttribute("especiales", productService.listarPorCategoria("especiales"));
        return "especiales"; // templates/especiales.html
    }
}
