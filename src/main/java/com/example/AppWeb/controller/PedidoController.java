package com.example.AppWeb.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.AppWeb.model.DetallePedido;
import com.example.AppWeb.model.Pedido;
import com.example.AppWeb.model.Producto;
import com.example.AppWeb.repository.ProductoRepository;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private ProductoRepository productoRepository;

    private Pedido pedidoActual = new Pedido();

    @GetMapping
    public String mostrarPedido(Model model) {
        model.addAttribute("pedido", pedidoActual);
        model.addAttribute("detalles", pedidoActual.getDetalles());
        model.addAttribute("total", pedidoActual.getTotal());
        return "pedido";
    }

    @PostMapping("/agregar")
    public String agregarProducto(
            @RequestParam(name = "productoId") Integer productoId,
            @RequestParam(name = "redirectUrl", required = false) String redirectUrl) {

        Optional<Producto> productoOpt = productoRepository.findById(productoId);

        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();

            Optional<DetallePedido> detalleOpt = pedidoActual.getDetalles().stream()
                    .filter(det -> det.getProducto().getId().equals(productoId))
                    .findFirst();

            if (detalleOpt.isPresent()) {
                detalleOpt.get().setCantidad(detalleOpt.get().getCantidad() + 1);
            } else {
                DetallePedido detalle = new DetallePedido();
                detalle.setProducto(producto);
                detalle.setCantidad(1);
                detalle.setPedido(pedidoActual);

                pedidoActual.getDetalles().add(detalle);
            }
        }

        return "redirect:" + (redirectUrl != null ? redirectUrl : "/pedido");
    }

    @PostMapping("/incrementar")
    public String incrementar(
            @RequestParam(name = "index") int index,
            @RequestParam(name = "redirectUrl", required = false) String redirectUrl) {

        if (index >= 0 && index < pedidoActual.getDetalles().size()) {
            DetallePedido detalle = pedidoActual.getDetalles().get(index);
            detalle.setCantidad(detalle.getCantidad() + 1);
        }

        return "redirect:" + (redirectUrl != null ? redirectUrl : "/pedido");
    }

    @PostMapping("/disminuir")
    public String disminuir(
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

    @PostMapping("/eliminar")
    public String eliminar(
            @RequestParam(name = "index") int index,
            @RequestParam(name = "redirectUrl", required = false) String redirectUrl) {

        if (index >= 0 && index < pedidoActual.getDetalles().size()) {
            pedidoActual.getDetalles().remove(index);
        }

        return "redirect:" + (redirectUrl != null ? redirectUrl : "/pedido");
    }

    @PostMapping("/finalizar")
    public String finalizar(Model model) {

        if (pedidoActual.getDetalles().isEmpty()) {
            model.addAttribute("error", "Debe agregar productos al pedido.");
            return "pedido";
        }

        pedidoActual.setFecha(LocalDate.now());
        model.addAttribute("mensaje", "¡Pedido enviado correctamente!");
        model.addAttribute("pedidoFinalizado", pedidoActual);

        pedidoActual = new Pedido();
        return "pedido";
    }
}
