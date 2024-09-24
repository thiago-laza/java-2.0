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

import com.produtos_api.dto.ProdutoDTO;
import com.produtos_api.model.Categoria;
import com.produtos_api.model.Produto;
import com.produtos_api.service.ProdutoService;

import jakarta.websocket.server.PathParam;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {
     @Autowired
    private ProdutoService produtoService;

    //CRUD

    @PostMapping
    public ResponseEntity<ProdutoDTO> cadastrarProduto(@RequestBody ProdutoDTO produtoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.salvarProduto(produtoDTO));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarProdutos(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.buscaTodosProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> obterProdutoPeloId(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.buscaProdutoPeloId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizarDadosDoProduto(@PathVariable("id") Long id, Produto produto){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.atualizaProduto(id, produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerProduto(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.deletaCategoria(id));
    }

    //OUTROS METODOS

    @GetMapping("/codigo")
    public ResponseEntity<ProdutoDTO> obterProdutoPeloCodigo(@PathParam("codigo") String codigo){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.buscaProdutoPeloCodigo(codigo));
    }

    @GetMapping("/nome")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoPeloNome(@PathParam("nome") String nome){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.buscaProdutoPeloNome(nome));
    }

    @GetMapping("descricao")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoPelaDescricao(@PathParam("descricao") String descricao){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.buscaProdutoPelaDescricao(descricao));
    }

    @GetMapping("/precoInferior/precoSuperior")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoPeloPreco(@PathParam("precoInferior") Double menorPreco, @PathParam("precoSuperior") Double maiorPreco){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.buscaProdutoPeloPreco(menorPreco, maiorPreco));
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<ProdutoDTO>> obterProdutoPelaCategoria(@RequestBody Categoria categoria){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.buscaProdutoPelaCategoria(categoria));
    }

    
    //Json:
    /*
     {
        "codigo": "xxxxxxx",
        "nome": "xxxxxx",
        "descricao": "xxxxxx",
        "preco": xxxxx,
        "categoria": {
            "id": xxxxx(gerado na categoria),
            "nome": "---"
            }
    }
    */
    

    

    
    
}
