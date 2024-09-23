package com.pedidos_api.dto;

import com.pedidos_api.model.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private Long id;
    private String codigoProduto;
    private Integer quantidade;
    private Double preco;

    public Item converteDtoParaItem(){
        Item item = new Item();

        item.setCodigoProduto(this.codigoProduto);
        item.setQuantidade(this.quantidade);
        item.setPreco(this.preco);

        return item;
    }
}
