package com.produtos_api.service;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produtos_api.dto.ProdutoDTO;
import com.produtos_api.model.Categoria;
import com.produtos_api.model.Produto;
import com.produtos_api.repository.ProdutoRepository;

@Service
public class ProdutoService {


    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoDTO salvarProduto(Produto produto){
        return produtoRepository.save(produto).converteProdutoParaDto();
    }

    public ProdutoDTO buscarPeloId(Long id){
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()) {
            return produtoOptional.get().converteProdutoParaDto();
        }

        return null;
    }

    public List<ProdutoDTO> listTodosProdutos(){
        List<Produto> produtoList = produtoRepository.findAll();
        List<ProdutoDTO> produtoDTOs = new ArrayList<>();

        for (Produto produto : produtoList){
            produtoDTOs.add(produto.converteProdutoParaDto());
        }

        return produtoDTOs;
    }

    public ProdutoDTO atualizaProduto(Long id, Produto produto){
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if (produtoOptional.isPresent()) {
            Produto produtoEncontrado = produtoOptional.get();

            produtoEncontrado.setCodigo(produto.getCodigo());
            produtoEncontrado.setNome(produto.getNome());
            produtoEncontrado.setDescricao(produto.getDescricao());
            produtoEncontrado.setPreco(produto.getPreco());
            produtoEncontrado.setDescricao(produto.getDescricao());

            produtoRepository.save(produtoEncontrado);
            return produtoEncontrado.converteProdutoParaDto();
        }

        return null;
    }

    public void deletaProduto(Long id){
        produtoRepository.deleteById(id);
    }

    public ProdutoDTO buscaPeloCodigo(String codigo){
        Optional<Produto> produtoOptional = produtoRepository.findByCodigo(codigo);

        if (produtoOptional.isPresent()) {
            return produtoOptional.get().converteProdutoParaDto();
        }

        return null;
    }

    public List<ProdutoDTO> buscaPeloNome(String nome){
        List<Produto> produtoList = produtoRepository.findByNomeContainsIgnoreCase(nome);
        List<ProdutoDTO> produtoDTOs = new ArrayList<>();

        for (Produto produto : produtoList){
            produtoDTOs.add(produto.converteProdutoParaDto());
        }

        return produtoDTOs;
        
    }

    public List<ProdutoDTO> buscaPelaDescricao(String descricao){
        List<Produto> produtoList = produtoRepository.findByDescricao(descricao);
        List<ProdutoDTO> produtoDTOs = new ArrayList<>();

        for (Produto produto : produtoList){
            produtoDTOs.add(produto.converteProdutoParaDto());
        }

        return produtoDTOs;
    }

    public List<ProdutoDTO> buscaPeloPreco(Double menorPreco, Double maiorPrece){
        List<Produto> produtoList = produtoRepository.findByPreco_Between(menorPreco, maiorPrece);
        List<ProdutoDTO> produtoDTOs = new ArrayList<>();

        for (Produto produto: produtoList){
            produtoDTOs.add(produto.converteProdutoParaDto());
        }

        return produtoDTOs;
    }

    public List<ProdutoDTO> buscaPelaCategoria(Categoria categoria){
        List<Produto> produtoList = produtoRepository.findByCategoria(categoria);
        List<ProdutoDTO> produtoDTOs = new ArrayList<>();

        for (Produto produto: produtoList){
            produtoDTOs.add(produto.converteProdutoParaDto());
        }

        return produtoDTOs;
    }

    
}
