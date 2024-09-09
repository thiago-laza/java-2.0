package com.usuarios.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorDTO {
    
    private Long id;
    private String nome;
    private String cnpj;
    private String razaoSocial;
    private String email;
    private String telefone;
    private String celular;
    private String cep;
    private String logradouro;
    private String cidade;
    private String uf;
    private String bairro;
    private String numero;
    private String complemento;
}
