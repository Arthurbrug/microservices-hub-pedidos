package com.github.arthurbrug.ms.pedidos.repositories;

import com.github.arthurbrug.ms.pedidos.entities.ItemDoPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDoPedidoRepository extends JpaRepository<ItemDoPedido, Long> {
}
