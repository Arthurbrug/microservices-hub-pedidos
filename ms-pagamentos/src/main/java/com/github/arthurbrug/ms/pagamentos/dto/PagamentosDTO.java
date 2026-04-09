package com.github.arthurbrug.ms.pagamentos.dto;

import com.github.arthurbrug.ms.pagamentos.entities.Pagamento;
import com.github.arthurbrug.ms.pagamentos.entities.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.print.attribute.standard.Media;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PagamentosDTO {
    private Long id;
    @NotNull(message = "O campo valor é obrigatório")
    @Positive(message = "O Valor do pagamento deve ser um numero positive")
    private BigDecimal valor;
    @NotBlank(message = "O campo nome e obrigatorio")
    @Size(min = 3, max = 50, message = "O nome deve entre 3 e 50 caracteres")
    private String nome;
    @NotBlank(message = "O campo numero do cartão é obrigatorio")
    @Size(min = 16, max = 16, message = "Numero do cartão dever ter 16 caracteres")
    private String numeroCartao;
    @NotBlank(message = "O campo validade é obrigatorio")
    @Size(min = 5, max = 5, message = "Validade do cartão deve ter 5 caracteres")
    private String validade;
    @NotBlank(message = "O codigo de segurança é obrigatorio")
    @Size(min = 3, max = 3, message = "Codigo de segurança deve ter 3 caracteres")
    private String codigoSegurancao;
    private Status status;
    @NotNull(message = "o campo ID do pedido é obrigatorio")
    private Long pedidoId;


    public PagamentosDTO(Pagamento pagamento){
        id = pagamento.getId();
        valor = pagamento.getValor();
        nome= pagamento.getNome();
        numeroCartao = pagamento.getNumeroCartao();
        validade = pagamento.getValidade();
        codigoSegurancao = pagamento.getCodigoSeguranca();
        status=pagamento.getStatus();
        pedidoId = pagamento.getPedidoId();
    }
}
