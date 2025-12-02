package com.example.AppWeb.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.example.AppWeb.model.Usuario;
import com.example.AppWeb.model.UsuarioRol;
import com.example.AppWeb.repository.UsuarioRepository;
import com.example.AppWeb.repository.UsuarioRolRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioRolRepository usuarioRolRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        List<UsuarioRol> roles = usuarioRolRepository.findByUsuarioId(usuario.getId());

        return new MyUserPrincipal(usuario, roles);
    }
}
