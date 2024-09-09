package com.produtos_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.produtos_api.dto.ProdutoDTO;
import com.produtos_api.model.Produto;
import com.produtos_api.repository.ProdutoRepository;



@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    //salvarProduto
    public Produto salvarProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    //buscarPeloId
    
    //buscarTodosProdutos
    
    //atualizarProduto
   
    //deletaProduto
    

    
}
