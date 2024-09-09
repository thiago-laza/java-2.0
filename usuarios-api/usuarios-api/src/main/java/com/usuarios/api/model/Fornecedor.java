package com.usuarios.api.model;

import com.usuarios.api.dto.FornecedorDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_fornecedores")
public class Fornecedor{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Usuario usuario;

    @Column(nullable = false, unique = true)
    private String razaoSocial;

    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    @Embedded
    private Endereco endereco;

    public FornecedorDTO converterFornecedorParaDto() {
        FornecedorDTO dto = new FornecedorDTO();

        dto.setId(id);
        dto.setNome(usuario.getNome());
        dto.setCnpj(cnpj);
        dto.setRazaoSocial(razaoSocial);
        dto.setEmail(usuario.getEmail());
        dto.setTelefone(usuario.getTelefone());
        dto.setCelular(usuario.getCelular());
        dto.setCep(endereco.getCep());
        dto.setLogradouro(endereco.getLogradouro());
        dto.setBairro(endereco.getBairro());
        dto.setCidade(endereco.getCidade());
        dto.setNumero(endereco.getNumero());
        dto.setComplemento(endereco.getComplemento());
        dto.setUf(endereco.getUf());

        return dto;
    }

    
}
