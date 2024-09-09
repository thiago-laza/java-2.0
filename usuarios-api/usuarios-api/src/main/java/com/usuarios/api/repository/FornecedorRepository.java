package com.usuarios.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usuarios.api.model.Fornecedor;
import java.util.List;



@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    
    Optional<Fornecedor> findByCnpj(String cnpj);
    
    List<Fornecedor> findByRazaoSocialContainsIgnoreCase(String razaoSocial);

    Optional<Fornecedor> findByUsuario_email(String email);

    List<Fornecedor> findByUsuario_NomeContainsIgnoreCase(String nome);
}
