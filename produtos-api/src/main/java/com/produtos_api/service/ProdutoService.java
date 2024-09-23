package com.produtos_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    
    //salvarProduto
    public ProdutoDTO salvarProduto(ProdutoDTO produtoDTO) {
        // Converte o ProdutoDTO diretamente para Produto
        Produto produto = produtoDTO.converteDtoParaProduto();
    
        // Salva o Produto no banco de dados
        Produto produtoSalvo = produtoRepository.save(produto);
    
        // Retorna o Produto salvo convertido novamente para DTO
        return produtoSalvo.converteProdutoParaDto();
    }
    
    //buscarPeloId
    public ProdutoDTO buscaProdutoPeloId(Long id){
        Optional<Produto> pOptional = produtoRepository.findById(id);

        if (pOptional.isPresent()) {
            return pOptional.get().converteProdutoParaDto();
        }

        return null;
    }
    //buscarTodosProdutos
    public List<ProdutoDTO> buscaTodosProdutos(){
        List<Produto> produtoList = produtoRepository.findAll();
        List<ProdutoDTO> produtoDTOs = new ArrayList<>();

        for (Produto produto: produtoList){
            produtoDTOs.add(produto.converteProdutoParaDto());
        }

        return produtoDTOs;
    }
    //atualizarProduto
    public ProdutoDTO atualizaProduto(Long id, Produto produto){
        Optional<Produto> pOptional = produtoRepository.findById(id);

        if (pOptional.isPresent()) {
            Produto produtoEncontrado = pOptional.get();

            
            produtoEncontrado.setCodigo(produto.getCodigo());
            produtoEncontrado.setNome(produto.getNome());
            produtoEncontrado.setDescricao(produto.getDescricao());
            produtoEncontrado.setPreco(produto.getPreco());
            produtoEncontrado.setCategoria(produto.getCategoria());

            produtoRepository.save(produtoEncontrado);
            return produtoEncontrado.converteProdutoParaDto();
        }

        return null;
    }   
    //deletaProduto/Optional
    public String deletaCategoria(Long id){
        Optional<Produto> pOptional = produtoRepository.findById(id);

        if (pOptional.isPresent()) {
            produtoRepository.deleteById(id);
        }

        return "Produto deletado com sucesso.";
    }

    //DEMAIS METODOS

    //findByCodigo -> Optional
    public ProdutoDTO buscaProdutoPeloCodigo(String codigo){
        Optional<Produto> pOptional = produtoRepository.findByCodigo(codigo);

        if (pOptional.isPresent()) {
            return pOptional.get().converteProdutoParaDto();
        }

        return null;
    }
    //findByNomeContainsIgnoreCase -> List
    public List<ProdutoDTO> buscaProdutoPeloNome(String nome){
        List<Produto> produtoList = produtoRepository.findByNomeContainsIgnoreCase(nome);
        List<ProdutoDTO> produtoDTOs = new ArrayList<>();

        for (Produto produto: produtoList){
            produtoDTOs.add(produto.converteProdutoParaDto());
        }

        return produtoDTOs;
    }
    //findByDescricao -> List
    public List<ProdutoDTO> buscaProdutoPelaDescricao(String descricao){
        List<Produto> produtoList = produtoRepository.findByDescricao(descricao);
        List<ProdutoDTO> produtoDTOs = new ArrayList<>();

        for (Produto produto: produtoList){
            produtoDTOs.add(produto.converteProdutoParaDto());
        }

        return produtoDTOs;
    }
    //findByPreco_Between -> List
    public List<ProdutoDTO> buscaProdutoPeloPreco(Double menorPreco, Double maiorPreco){
        List<Produto> produtoList = produtoRepository.findByPreco_Between(menorPreco, maiorPreco);
        List<ProdutoDTO> produtoDTOs = new ArrayList<>();

        for (Produto produto: produtoList){
            produtoDTOs.add(produto.converteProdutoParaDto());
        }

        return produtoDTOs;
    }
    //findByCategoria -> List
    public List<ProdutoDTO> buscaProdutoPelaCategoria(Categoria categoria){
        List<Produto> produtoList = produtoRepository.findByCategoria(categoria);
        List<ProdutoDTO> produtoDTOs = new ArrayList<>();

        for (Produto produto: produtoList){
            produtoDTOs.add(produto.converteProdutoParaDto());
        }

        return produtoDTOs;
    }
    
   

    
}
