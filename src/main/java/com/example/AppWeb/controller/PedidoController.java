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
import com.example.AppWeb.repository.PedidoRepository;
import com.example.AppWeb.repository.ProductoRepository;

@Controller
@RequestMapping("/pedido")
@SessionAttributes("pedidoActual") // ✅ Mantener pedido por sesión
public class PedidoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    // ✅ Se crea un pedido nuevo por sesión de usuario
    @ModelAttribute("pedidoActual")
    public Pedido pedidoActual() {
        return new Pedido();
    }

    @GetMapping
    public String mostrarPedido(@ModelAttribute("pedidoActual") Pedido pedidoActual, Model model) {
        model.addAttribute("pedido", pedidoActual);
        model.addAttribute("detalles", pedidoActual.getDetalles());
        model.addAttribute("total", pedidoActual.getTotal());
        return "pedido";
    }

    @PostMapping("/agregar")
    public String agregarProducto(@ModelAttribute("pedidoActual") Pedido pedidoActual,
                                  @RequestParam("productoId") Integer productoId,
                                  @RequestParam(value = "redirectUrl", required = false) String redirectUrl) {

        Producto producto = productoRepository.findById(productoId).orElse(null);
        if (producto == null) return "redirect:/pedido";

        Optional<DetallePedido> existente = pedidoActual.getDetalles().stream()
                .filter(d -> d.getProducto().getId().equals(productoId))
                .findFirst();

        if (existente.isPresent()) {
            existente.get().setCantidad(existente.get().getCantidad() + 1);
        } else {
            DetallePedido detalle = new DetallePedido();
            detalle.setProducto(producto);
            detalle.setCantidad(1);
            detalle.setPedido(pedidoActual);
            pedidoActual.getDetalles().add(detalle);
        }

        return "redirect:" + (redirectUrl != null ? redirectUrl : "/pedido");
    }

    @PostMapping("/incrementar")
    public String incrementar(@ModelAttribute("pedidoActual") Pedido pedidoActual,
                              @RequestParam("index") int index,
                              @RequestParam(value = "redirectUrl", required = false) String redirectUrl) {

        if (index >= 0 && index < pedidoActual.getDetalles().size()) {
            DetallePedido d = pedidoActual.getDetalles().get(index);
            d.setCantidad(d.getCantidad() + 1);
        }

        return "redirect:" + (redirectUrl != null ? redirectUrl : "/pedido");
    }

    @PostMapping("/disminuir")
    public String disminuir(@ModelAttribute("pedidoActual") Pedido pedidoActual,
                            @RequestParam("index") int index,
                            @RequestParam(value = "redirectUrl", required = false) String redirectUrl) {

        if (index >= 0 && index < pedidoActual.getDetalles().size()) {
            DetallePedido d = pedidoActual.getDetalles().get(index);

            if (d.getCantidad() > 1) {
                d.setCantidad(d.getCantidad() - 1);
            } else {
                pedidoActual.getDetalles().remove(index);
            }
        }

        return "redirect:" + (redirectUrl != null ? redirectUrl : "/pedido");
    }

    @PostMapping("/eliminar")
    public String eliminar(@ModelAttribute("pedidoActual") Pedido pedidoActual,
                           @RequestParam("index") int index,
                           @RequestParam(value = "redirectUrl", required = false) String redirectUrl) {

        if (index >= 0 && index < pedidoActual.getDetalles().size()) {
            pedidoActual.getDetalles().remove(index);
        }

        return "redirect:" + (redirectUrl != null ? redirectUrl : "/pedido");
    }

    @PostMapping("/finalizar")
    public String finalizar(@ModelAttribute("pedidoActual") Pedido pedidoActual, Model model) {

        if (pedidoActual.getDetalles().isEmpty()) {
            model.addAttribute("error", "Debe agregar productos al pedido.");
            return "pedido";
        }

        pedidoActual.setFecha(LocalDate.now());
        pedidoActual.getDetalles().forEach(d -> d.setPedido(pedidoActual));
        pedidoRepository.save(pedidoActual); // ✅ Aquí ahora sí se guarda bien

        model.addAttribute("mensaje", "✅ ¡Pedido enviado correctamente!");
        model.addAttribute("pedidoFinalizado", pedidoActual);

        // ✅ Reiniciar pedido en sesión
        model.addAttribute("pedidoActual", new Pedido());

        return "pedido";
    }
}
