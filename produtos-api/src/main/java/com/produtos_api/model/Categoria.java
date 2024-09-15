package com.produtos_api.model;

import java.util.List;

import com.produtos_api.dto.CategoriaDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
    
    @OneToMany
    private List<Produto> produtos;

    public CategoriaDTO converteCategoriaParaDTO(){
        CategoriaDTO dto = new CategoriaDTO();

        dto.setNome(nome);

        return dto;
    }

}
