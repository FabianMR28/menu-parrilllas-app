package com.example.AppWeb.model;

public class DetallePedido {

    private Integer id; // ya no se genera automáticamente
    private Pedido pedido; // relación simple en memoria
    private Producto producto;
    private int cantidad;

    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
