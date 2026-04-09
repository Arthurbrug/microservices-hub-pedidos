package com.github.arthurbrug.ms.pagamentos.controller;

import com.github.arthurbrug.ms.pagamentos.dto.PagamentosDTO;
import com.github.arthurbrug.ms.pagamentos.service.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public ResponseEntity<List<PagamentosDTO>> getAll(){
        List<PagamentosDTO> pagamentosDTOS = pagamentoService.findAllPagamentos();
        return ResponseEntity.ok(pagamentosDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentosDTO> getById(@PathVariable Long id){

        PagamentosDTO pagamentosDTO = pagamentoService.findPagamentosById(id);
        return ResponseEntity.ok(pagamentosDTO);
    }

    @PostMapping
    private ResponseEntity<PagamentosDTO> createPagamento(@RequestBody @Valid PagamentosDTO pagamentosDTO){
        pagamentosDTO = pagamentoService.savePagamento(pagamentosDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(pagamentosDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(pagamentosDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentosDTO> updateProduto(@PathVariable Long id,
                                                       @Valid @RequestBody PagamentosDTO pagamentosDTO){
        pagamentosDTO = pagamentoService.updatePagamento(id, pagamentosDTO);

        return ResponseEntity.ok(pagamentosDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PagamentosDTO> deletePagamento(@PathVariable Long id){
        pagamentoService.deletePagamentoById(id);
        return ResponseEntity.noContent().build();
    }

}
