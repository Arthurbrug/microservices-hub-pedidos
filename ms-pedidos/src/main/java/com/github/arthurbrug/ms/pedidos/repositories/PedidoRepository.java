package com.github.arthurbrug.ms.pedidos.repositories;

import com.github.arthurbrug.ms.pedidos.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
