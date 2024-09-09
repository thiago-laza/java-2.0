package com.usuarios.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usuarios.api.model.Cliente;
import java.time.LocalDate;



@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    Optional<Cliente> findByCpf(String cpf);

    List<Cliente> findByDataNascimento(LocalDate dataNascimento);

    List<Cliente> findByDataNascimentoBetween(LocalDate dataInicio, LocalDate dataFinal);

    Optional<Cliente> findByUsuario_Email(String email);

    List<Cliente> findByUsuario_NomeContainsIgnoreCase(String nome);

}
