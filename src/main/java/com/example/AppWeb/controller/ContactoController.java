package com.example.AppWeb.controller;

import com.example.AppWeb.model.ContactoForm;
import com.example.AppWeb.model.Contacto;
import com.example.AppWeb.repository.ContactoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contacto")
public class ContactoController {

    private final ContactoRepository contactoRepository;

    public ContactoController(ContactoRepository contactoRepository) {
        this.contactoRepository = contactoRepository;
    }

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("contactoForm", new ContactoForm());
        return "contactoForm";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute ContactoForm contactoForm) {

        Contacto contacto = new Contacto();
        contacto.setNombre(contactoForm.getNombre());
        contacto.setEmail(contactoForm.getEmail());
        contacto.setCelular(contactoForm.getCelular());
        contacto.setCiudad(contactoForm.getCiudad());
        contacto.setGenero(contactoForm.getGenero());
        contacto.setContactaste(contactoForm.getContactaste());
        contacto.setMensaje(contactoForm.getMensaje());

        contactoRepository.save(contacto);

        return "redirect:/contacto/form?success";
    }
}
