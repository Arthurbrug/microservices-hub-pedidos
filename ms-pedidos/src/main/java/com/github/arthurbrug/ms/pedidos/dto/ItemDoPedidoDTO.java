package com.github.arthurbrug.ms.pedidos.dto;

import com.github.arthurbrug.ms.pedidos.entities.ItemDoPedido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ItemDoPedidoDTO {
    private Long id;

    @NotNull(message = "Quantidade requerido")
    @Positive(message = "Quantidade deve ser um numero positivo")
    private Integer quantidade;
    @NotBlank(message = "Descriçao requerido")
    private String descricao;
    @NotNull(message = "Preço unitario é requirido")
    @Positive(message = "O Preço unitario deve ser um valor positive e maior que zero")
    private BigDecimal precoUnitario;

    public ItemDoPedidoDTO(ItemDoPedido itemDoPedido){
        id = itemDoPedido.getId();
        quantidade = itemDoPedido.getQuantidade();
        descricao = itemDoPedido.getDescricao();
        precoUnitario = itemDoPedido.getPrecoUnitario();
    }
}
