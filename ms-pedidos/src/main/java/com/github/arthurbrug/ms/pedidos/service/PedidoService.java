package com.github.arthurbrug.ms.pedidos.service;

import com.github.arthurbrug.ms.pedidos.dto.PedidoDTO;
import com.github.arthurbrug.ms.pedidos.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional(readOnly = true)
    public List<PedidoDTO> findAllPedidos(){
        return pedidoRepository.findAll().stream().map(PedidoDTO::new).toList();
    }
}
