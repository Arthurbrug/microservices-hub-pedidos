package com.github.arthurbrug.ms.pagamentos.tests;

import com.github.arthurbrug.ms.pagamentos.entities.Pagamento;
import com.github.arthurbrug.ms.pagamentos.entities.Status;

import java.math.BigDecimal;

public class Factory {

    public static Pagamento createdPagamento(){
        Pagamento pagamento = new Pagamento(
                1L, BigDecimal.valueOf(32.25), "Maicon", "1234567890123456",
                "05/16", "430", Status.CRIADO, 1L);
        return pagamento;
    }

    public static Pagamento createdPagamentoSemId(){
        Pagamento pagamento = createdPagamento();
        pagamento.setId(null);
        return pagamento;
    }
}
