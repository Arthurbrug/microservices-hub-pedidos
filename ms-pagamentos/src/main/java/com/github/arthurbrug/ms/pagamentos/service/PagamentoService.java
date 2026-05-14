package com.github.arthurbrug.ms.pagamentos.service;

import com.github.arthurbrug.ms.pagamentos.client.PedidoClient;
import com.github.arthurbrug.ms.pagamentos.dto.PagamentosDTO;
import com.github.arthurbrug.ms.pagamentos.entities.Pagamento;
import com.github.arthurbrug.ms.pagamentos.entities.Status;
import com.github.arthurbrug.ms.pagamentos.exceptions.PagamentosAprovadoException;
import com.github.arthurbrug.ms.pagamentos.exceptions.ResourceNotFoundException;
import com.github.arthurbrug.ms.pagamentos.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PedidoClient pedidoClient;



    @Transactional(readOnly = true)
    public List<PagamentosDTO> findAllPagamentos(){
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        return pagamentos.stream().map(PagamentosDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public PagamentosDTO findPagamentosById(Long id){
        Pagamento pagamento = pagamentoRepository.findById(id).orElseThrow(
                () -> new ResolutionException("Recurso não encontrado. Id: " + id)
        );

        return new PagamentosDTO(pagamento);
    }

    @Transactional
    public PagamentosDTO savePagamento(PagamentosDTO pagamentosDTO){
        Pagamento pagamento = new Pagamento();
        mapperDtoToPagamento(pagamentosDTO, pagamento);
        pagamento.setStatus(Status.CRIADO);
        pagamento = pagamentoRepository.save(pagamento);
        return new PagamentosDTO(pagamento);
    }

    public void  mapperDtoToPagamento(PagamentosDTO pagamentosDTO, Pagamento pagamento){
        pagamento.setValor(pagamentosDTO.getValor());
        pagamento.setNome(pagamentosDTO.getNome());
        pagamento.setNumeroCartao(pagamentosDTO.getNumeroCartao());
        pagamento.setValidade((pagamentosDTO.getValidade()));
        pagamento.setCodigoSeguranca(pagamentosDTO.getCodigoSegurancao());
        pagamento.setPedidoId(pagamentosDTO.getPedidoId());
    }

    @Transactional
    public void deletePagamentoById(Long id){
        if (!pagamentoRepository.existsById(id)){
            throw new ResourceNotFoundException("recurso nao encontrado ID: " + id);
        }
        pagamentoRepository.deleteById(id);
    }



    @Transactional
    public PagamentosDTO confirmarPagamentoDoPedido(Long id) {

        Pagamento pagamento = pagamentoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pagamento não encontrado. ID: " + id)
        );

        pagamento.setStatus(Status.APROVADO);
        pagamentoRepository.save(pagamento);

        pedidoClient.confirmarPagamento(pagamento.getPedidoId());

        return new PagamentosDTO(pagamento);
    }

    @Transactional
    public PagamentosDTO updatePagamento(Long id, PagamentosDTO pagamentoDTO){

        try {
            Pagamento pagamento = pagamentoRepository.getReferenceById(id);
            if (pagamento.getStatus().equals(Status.APROVADO)){
                throw new PagamentosAprovadoException(
                        String.format("Pagamento id %d já está APROVADO e não pode ser alterado", id)
                );
            }
           mapperDtoToPagamento(pagamentoDTO, pagamento);
            pagamento.setStatus(pagamentoDTO.getStatus());
            pagamento = pagamentoRepository.save(pagamento);
            return new PagamentosDTO(pagamento);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }
    }

}
