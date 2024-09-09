package com.produtos_api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {


    private Long id;
    private String codigo;
    private String nome;
    private String descricao;
    private Double preco;
    private String categoria;
}
