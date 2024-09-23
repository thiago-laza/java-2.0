package com.pedidos_api.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedidos_api.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long>{

    Optional<Item> findByCodigoProduto(String codigoProduto);

    List<Item> findByQuantidade(Integer quantidade);

    List<Item> findByPreco(Double preco);
}
