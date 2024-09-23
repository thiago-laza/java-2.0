package com.pedidos_api.model;

import com.pedidos_api.dto.ItemDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_itens")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "codigo_produto")
    private String codigoProduto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Double preco;


    public ItemDTO converteItemParaDto(){
        ItemDTO dto = new ItemDTO();

        dto.setCodigoProduto(codigoProduto);
        dto.setQuantidade(quantidade);
        dto.setPreco(preco);

        return dto;
    }


    
}
