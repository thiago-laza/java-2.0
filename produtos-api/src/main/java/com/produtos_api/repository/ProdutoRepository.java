package com.produtos_api.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.produtos_api.model.Categoria;
import com.produtos_api.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    Optional<Produto> findByCodigo(String codigo);
    
    List<Produto> findByNomeContainsIgnoreCase(String nome);

    List<Produto> findByDescricao(String descricao);

    List<Produto> findByPreco_Between(Double menorPreco, Double maiorPreco);

    List<Produto> findByCategoria(Categoria categoria);

}
