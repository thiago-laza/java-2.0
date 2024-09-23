package com.pedidos_api.dto;

import java.time.LocalDate;
import java.util.List;

import com.pedidos_api.model.Item;
import com.pedidos_api.model.Pedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private Long id;
    private String emailCliente;
    private Double total;
    private LocalDate data;
    private List<Item> item;


    public Pedido converteDtoParaPedido(){
        Pedido pedido = new Pedido();

        pedido.setEmailCliente(this.emailCliente);
        pedido.setTotal(this.total);
        pedido.setData(this.data);
        pedido.setItens(this.item);

        return pedido;
    }
}
