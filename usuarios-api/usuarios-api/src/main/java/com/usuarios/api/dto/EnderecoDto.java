package com.usuarios.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDto {

    private String cep;
    private String logradouro;
    private String localidade;
    private String uf;
    private String bairro;
    
}
