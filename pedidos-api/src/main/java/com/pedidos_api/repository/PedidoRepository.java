package com.pedidos_api.repository;


import java.time.LocalDate;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedidos_api.model.Item;
import com.pedidos_api.model.Pedido;



@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long>{


    Optional<Pedido> findByEmailCliente(String emailCliente);

    List<Pedido> findByTotal(Double total);

    List<Pedido> findByData(LocalDate data);

    List<Pedido> findByItensIn(List<Item> item);
}
