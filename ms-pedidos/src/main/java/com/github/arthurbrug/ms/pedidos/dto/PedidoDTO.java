package com.github.arthurbrug.ms.pedidos.dto;

import com.github.arthurbrug.ms.pedidos.entities.ItemDoPedido;
import com.github.arthurbrug.ms.pedidos.entities.Pedido;
import com.github.arthurbrug.ms.pedidos.entities.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PedidoDTO {

    private Long id;

    @NotBlank(message = "Nome requirido")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 a 100 caracteres")
    private String nome;

    @NotBlank(message = "CPF requirido")
    @Size(min = 11, max = 11, message = "O cpf deve ter 11 caracteres")
    private String cpf;
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    private Status status;
    private BigDecimal valorTotal;
    private List<@Valid ItemDoPedidoDTO> itens = new ArrayList<>();


    public PedidoDTO(Pedido pedido){
        id = pedido.getId();
        nome = pedido.getNome();
        cpf = pedido.getCpf();
        data = pedido.getData();
        status = pedido.getStatus();
        valorTotal = pedido.getValorTotal();

        // para os itens do pedido
        for (ItemDoPedido item : pedido.getItens()){
            ItemDoPedidoDTO itemDTO = new ItemDoPedidoDTO(item);
            itens.add(itemDTO);
        }
    }

}
