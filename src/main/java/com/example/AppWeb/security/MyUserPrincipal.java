package com.example.AppWeb.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.AppWeb.model.Usuario;
import com.example.AppWeb.model.UsuarioRol;

public class MyUserPrincipal implements UserDetails {

    private Usuario usuario;
    private Collection<UsuarioRol> roles;

    public MyUserPrincipal(Usuario usuario, Collection<UsuarioRol> roles) {
        this.usuario = usuario;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.getRol().getNombre())) 
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() { return usuario.getPassword(); }

    @Override
    public String getUsername() { return usuario.getUsername(); }

    public String getCorreo() { return usuario.getCorreo(); }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return usuario.isEnabled(); }
}
