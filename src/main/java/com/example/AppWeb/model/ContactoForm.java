package com.example.AppWeb.model;

import java.util.List;

public class ContactoForm {
    private String nombre;
    private String email;
    private String celular;
    private String ciudad;
    private String genero;
    private List<String> contactaste; // checkboxes
    private String mensaje;

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public List<String> getContactaste() { return contactaste; }
    public void setContactaste(List<String> contactaste) { this.contactaste = contactaste; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}
