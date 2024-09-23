package com.produtos_api.controller;

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

import com.produtos_api.dto.CategoriaDTO;
import com.produtos_api.model.Categoria;
import com.produtos_api.service.CategoriaService;

import jakarta.websocket.server.PathParam;


@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    public CategoriaService categoriaService;

    //CRUD

    @PostMapping
    public ResponseEntity<Categoria> cadastrarCategoria(@RequestBody Categoria categoria){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.salvarCategoria(categoria));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listaCategorias(){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.listarCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> exibirCategoria(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.buscaCategoriaPeloId(id));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizarDadosDaCategoria(@PathVariable("id") Long id, @RequestBody Categoria categoria){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.atualizaCategoria(id, categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaCategoria(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.excluirCategoria(id));
    }

    //OUTROS METODOS

    @GetMapping("/nome")
    public ResponseEntity<List<CategoriaDTO>> obterProdutoPeloNome(@PathParam("nome") String nome){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.buscaCategoriaPeloNome(nome));
    }
}
