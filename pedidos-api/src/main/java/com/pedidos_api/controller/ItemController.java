package com.pedidos_api.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.pedidos_api.dto.ItemDTO;
import com.pedidos_api.model.Item;
import com.pedidos_api.service.ItemService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;

    //Endpoints (CRUD)

    @PostMapping
    public ResponseEntity<ItemDTO> cadastrarItem(@RequestBody ItemDTO itemDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.salvarItem(itemDTO));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> obterItemPeloId(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(itemService.buscaItemPeloId(id));
    }
    
    @GetMapping
    public ResponseEntity<List<ItemDTO>> listaItens(){
        return ResponseEntity.status(HttpStatus.OK).body(itemService.buscaTodosItens());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> atualizaItem(@PathVariable("id") Long id, Item item){
        return ResponseEntity.status(HttpStatus.OK).body(itemService.atualizaItem(id, item));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaItem(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(itemService.deletaItem(id));
    }


    //Endpoints (especificos)

    @GetMapping("/codigo")
    public ResponseEntity<ItemDTO> buscaItemPeloCodigo(@PathParam("codigo") String codigoProduto){
        return ResponseEntity.status(HttpStatus.OK).body(itemService.buscaItemPeloCodigo(codigoProduto));
    }
    
    @GetMapping("/quantidade")
    public ResponseEntity<List<ItemDTO>> buscaItemPelaQuantidade(@PathParam("quantidade") Integer quantidade){
        return ResponseEntity.status(HttpStatus.OK).body(itemService.buscaItemPelaQuantidade(quantidade));
    }
    
    @GetMapping("/preco")
    public ResponseEntity<List<ItemDTO>> buscaItemPeloPreco(@PathParam("preco") Double preco){
        return ResponseEntity.status(HttpStatus.OK).body(itemService.buscaItemPeloPreco(preco));
    }
}
