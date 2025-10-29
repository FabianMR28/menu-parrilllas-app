package com.example.AppWeb.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Long id;
    private LocalDate fecha;
    private List<DetallePedido> detalles = new ArrayList<>();

    public Pedido() {
        this.detalles = new ArrayList<>();
        this.fecha = LocalDate.now();
    }
    
    public Pedido(Long id, LocalDate fecha) {
        this.id = id;
        this.fecha = fecha;
        this.detalles = new ArrayList<>();
    }

    public Pedido(Long id) {
        this.id = id;
        this.fecha = LocalDate.now();
    }

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public List<DetallePedido> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<DetallePedido> detalles) {
		this.detalles = detalles;
	}
    
    public double getTotal() {
        return detalles.stream()
                       .mapToDouble(d -> d.getProducto().getPrecio() * d.getCantidad())
                       .sum();
    }
}
