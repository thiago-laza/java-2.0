package com.produtos_api.dto;

import com.produtos_api.model.Categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {

    private Long id;
    private String nome;

    public Categoria converteDtoParaCategoria(){
        Categoria categoria = new Categoria();
    
        categoria.setNome(nome);
    
        return categoria;
    }
}



