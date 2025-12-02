package com.example.AppWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.AppWeb.model.Usuario;
import com.example.AppWeb.model.Rol;
import com.example.AppWeb.model.UsuarioRol;
import com.example.AppWeb.repository.UsuarioRepository;
import com.example.AppWeb.repository.RolRepository;
import com.example.AppWeb.repository.UsuarioRolRepository;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRolRepository usuarioRolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ----- LOGIN -----
    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        return "usuario/login";  // Vista: src/main/resources/templates/usuario/login.html
    }

    // ----- REGISTRO -----
    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario/registro";
    }

    @PostMapping("/registro")
    public String procesarRegistro(@ModelAttribute Usuario usuario, Model model) {

        // Validaciones
        if (usuarioRepository.findByUsername(usuario.getUsername()) != null) {
            model.addAttribute("error", "El nombre de usuario ya está en uso.");
            return "usuario/registro";
        }

        if (usuarioRepository.findByCorreo(usuario.getCorreo()) != null) {
            model.addAttribute("error", "El correo ya está registrado.");
            return "usuario/registro";
        }

        // Cifrar contraseña
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setEnabled(true);

        // Guardar usuario
        usuarioRepository.save(usuario);

        // Asignar rol CLIENTE por defecto
        Rol rolCliente = rolRepository.findByNombre("ROLE_CLIENTE");
        if (rolCliente == null) {
            rolCliente = new Rol();
            rolCliente.setNombre("ROLE_CLIENTE");
            rolRepository.save(rolCliente);
        }

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rolCliente);
        usuarioRolRepository.save(usuarioRol);

        model.addAttribute("exito", "Registro exitoso. Ahora puedes iniciar sesión.");
        return "usuario/login";
    }
}
