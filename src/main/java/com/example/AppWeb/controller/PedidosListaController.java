package com.example.AppWeb.controller;

import com.example.AppWeb.repository.PedidoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PedidosListaController {

    private final PedidoRepository pedidoRepository;

    public PedidosListaController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping("/pedidos/lista")
    public String listarPedidos(Model model) {
        model.addAttribute("pedidos", pedidoRepository.findAll());
        return "pedidosLista"; // <-- nombre de la vista (Thymeleaf)
    }
}
