package com.example.AppWeb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.AppWeb.model.ContactoForm;

@Controller
public class ContactoController {

	
	private List<ContactoForm> contactos = new ArrayList<ContactoForm>();
	
    // Mostrar fomr de contacto
    @GetMapping("/contacto")
    public String mostrarFormulario(Model model) {
        model.addAttribute("contactoForm", new ContactoForm());
        return "contacto"; // corresponde a contacto.html
    }

    // Enviar datos del form contacto
    @PostMapping("/contacto")
    public String procesarFormulario(@ModelAttribute ContactoForm contactoForm, Model model) {
        model.addAttribute("contacto", contactoForm);
        contactos.add(contactoForm);
        return "index";
    }
	
}
