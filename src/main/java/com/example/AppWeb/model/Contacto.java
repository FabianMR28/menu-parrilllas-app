package com.example.AppWeb.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "contactos")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String email;
    private String celular;
    private String ciudad;
    private String genero;

    @ElementCollection
    @CollectionTable(name = "contacto_metodos_contacto", joinColumns = @JoinColumn(name = "contacto_id"))
    @Column(name = "metodo_contacto")
    private List<String> contactaste;

    private String mensaje;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public List<String> getContactaste() {
		return contactaste;
	}

	public void setContactaste(List<String> contactaste) {
		this.contactaste = contactaste;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

    
    
   
}
