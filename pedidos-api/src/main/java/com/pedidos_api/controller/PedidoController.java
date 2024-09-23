package com.pedidos_api.controller;

import java.util.List;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.pedidos_api.dto.PedidoDTO;
import com.pedidos_api.model.Item;
import com.pedidos_api.model.Pedido;
import com.pedidos_api.service.PedidoService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {


    @Autowired
    private PedidoService pedidoService;

    //Endpoints (CRUD)

    @PostMapping
    public ResponseEntity<PedidoDTO> cadastrarPedido(@RequestBody PedidoDTO pedidoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.salvarPedido(pedidoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> obterPedidoPeloId(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.buscaPedidoPeloId(id));
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarPedidos(){
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.buscaTodosPedidos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> atualizarPedido(@PathVariable("id") Long id, Pedido pedido){
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.atualizarPedido(id, pedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerPedido(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.deletaPedido(id));
    }
    //Endpoints (especificos)

    @GetMapping("/email")
    public ResponseEntity<PedidoDTO> buscaPedidoPeloEmail(@PathParam("email") String emailCliente){
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.buscaPedidoPeloEmail(emailCliente));
    }
    
    @GetMapping("/total")
    public ResponseEntity<List<PedidoDTO>> buscaPedidoPeloTotal(@RequestParam("total") Double total){
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.buscaPedidoPeloTotal(total));
    }

    @GetMapping("/data")
    public ResponseEntity<List<PedidoDTO>> buscaPedidoPelaData(@RequestParam("data") LocalDate data){
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.buscaPedidoPelaData(data));
    }

    @GetMapping("/item")
    public ResponseEntity<List<PedidoDTO>> buscaListaDePedido(@RequestParam("itens") List<Item> itens){
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.buscaListaDeItens(itens));
    }
}
