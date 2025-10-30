package com.example.AppWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.AppWeb.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
