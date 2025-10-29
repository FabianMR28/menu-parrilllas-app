package com.example.AppWeb.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.AppWeb.model.DetallePedido;
import com.example.AppWeb.model.Pedido;
import com.example.AppWeb.model.Producto;
import com.example.AppWeb.services.ProductService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private ProductService productService;

    // Lista de pedidos guardados
    private final List<Pedido> listaPedidos = new ArrayList<>();

    // Pedido actual
    private Pedido pedidoActual = new Pedido(1L, LocalDate.now());

    // Mostrar pedido
    @GetMapping
    public String mostrarPedido(Model model) {
        model.addAttribute("pedido", pedidoActual);
        model.addAttribute("detalles", pedidoActual.getDetalles());
        model.addAttribute("total", pedidoActual.getTotal());
        return "pedido";
    }

    // Agregar producto al pedido
    @PostMapping("/agregar")
    public String agregarProducto(
            @RequestParam(name = "productoId") Long productoId,
            @RequestParam(name = "redirectUrl", required = false) String redirectUrl) {

        Optional<Producto> prodOpt = productService.listarTodos().stream()
                .filter(p -> p.getId().equals(productoId))
                .findFirst();

        if (prodOpt.isPresent()) {
            Producto producto = prodOpt.get();

            Optional<DetallePedido> detalleOpt = pedidoActual.getDetalles().stream()
                    .filter(d -> d.getProducto().getId().equals(productoId))
                    .findFirst();

            if (detalleOpt.isPresent()) {
                detalleOpt.get().setCantidad(detalleOpt.get().getCantidad() + 1);
            } else {
                pedidoActual.getDetalles().add(new DetallePedido(producto, 1));
            }
        }

        return "redirect:" + (redirectUrl != null ? redirectUrl : "/pedido");
    }

    // Incrementar cantidad
    @PostMapping("/incrementar")
    public String incrementarCantidad(
            @RequestParam(name = "index") int index,
            @RequestParam(name = "redirectUrl", required = false) String redirectUrl) {

        if (index >= 0 && index < pedidoActual.getDetalles().size()) {
            DetallePedido detalle = pedidoActual.getDetalles().get(index);
            detalle.setCantidad(detalle.getCantidad() + 1);
        }
        return "redirect:" + (redirectUrl != null ? redirectUrl : "/pedido");
    }

    // Disminuir cantidad
    @PostMapping("/disminuir")
    public String disminuirCantidad(
            @RequestParam(name = "index") int index,
            @RequestParam(name = "redirectUrl", required = false) String redirectUrl) {

        if (index >= 0 && index < pedidoActual.getDetalles().size()) {
            DetallePedido detalle = pedidoActual.getDetalles().get(index);
            if (detalle.getCantidad() > 1) {
                detalle.setCantidad(detalle.getCantidad() - 1);
            } else {
                pedidoActual.getDetalles().remove(index);
            }
        }
        return "redirect:" + (redirectUrl != null ? redirectUrl : "/pedido");
    }

    // Eliminar detalle
    @PostMapping("/eliminar")
    public String eliminarDetalle(
            @RequestParam(name = "index") int index,
            @RequestParam(name = "redirectUrl", required = false) String redirectUrl) {

        if (index >= 0 && index < pedidoActual.getDetalles().size()) {
            pedidoActual.getDetalles().remove(index);
        }
        return "redirect:" + (redirectUrl != null ? redirectUrl : "/pedido");
    }

    // Finalizar pedido
    @PostMapping("/finalizar")
    public String finalizarPedido(Model model) {
        if (pedidoActual.getDetalles().isEmpty()) {
            model.addAttribute("error", "El pedido está vacío.");
            return "pedido";
        }

        // Guardar el pedido actual en la lista
        listaPedidos.add(pedidoActual);

        // Mostrar mensaje de confirmación
        model.addAttribute("mensaje", "¡Pedido enviado correctamente!");
        model.addAttribute("pedidoFinalizado", pedidoActual);

        // Crear un nuevo pedido para seguir usando
        pedidoActual = new Pedido(pedidoActual.getId() + 1, LocalDate.now());

        return "pedido";
    }

    // Obtener la lista de pedidos (opcional, para administración)
    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }
}
