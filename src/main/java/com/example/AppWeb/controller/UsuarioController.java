package com.example.AppWeb.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.AppWeb.model.Usuario;
import com.example.AppWeb.repository.UsuarioRepository;

@Controller
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    
    public UsuarioController(UsuarioRepository usuarioRepository) {
    	this.usuarioRepository = usuarioRepository;
    }

    // Para login
    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario/login";
    }
    
    @PostMapping("/login")
    public String procesarLogin(@ModelAttribute Usuario usuario, Model model) {
        Optional<Usuario> optUsuario = usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario());

        if (optUsuario.isPresent()) {
            Usuario existente = optUsuario.get();

            // Validar contraseña
            if (existente.getContrasena().equals(usuario.getContrasena())) {

                if (Boolean.FALSE.equals(existente.getActivo())) {
                    model.addAttribute("error", "Tu cuenta está inactiva. Contacta con el administrador.");
                    return "login";
                }

                // Redirección según rol
                if ("ADMIN".equalsIgnoreCase(existente.getRol())) {
                    return "redirect:/admin/dashboard";
                } else {
                    return "index";
                }
            }
        }

        model.addAttribute("error", "Usuario o contraseña incorrectos");
        return "usuario/login";
    }

    // Para registro
    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario/registro";
    }

    // --- Procesar registro ---
    @PostMapping("/registro")
    public String procesarRegistro(@ModelAttribute Usuario usuario, Model model) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario());
        Optional<Usuario> correoExistente = usuarioRepository.findByCorreo(usuario.getCorreo());

        if (usuarioExistente.isPresent()) {
            model.addAttribute("error", "El nombre de usuario ya está en uso.");
            return "usuario/registro";
        }

        if (correoExistente.isPresent()) {
            model.addAttribute("error", "El correo ya está registrado.");
            return "usuario/registro";
        }

        // Por defecto, todo usuario nuevo es CLIENTE y activo
        usuario.setRol("CLIENTE");
        usuario.setActivo(true);
        usuarioRepository.save(usuario);

        model.addAttribute("exito", "Registro exitoso. Ahora puedes iniciar sesión.");
        model.addAttribute("usuario", new Usuario());
        return "usuario/login";
    }
}
