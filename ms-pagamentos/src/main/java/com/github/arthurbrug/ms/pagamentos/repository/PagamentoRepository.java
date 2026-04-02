package com.github.arthurbrug.ms.pagamentos.repository;

import com.github.arthurbrug.ms.pagamentos.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
