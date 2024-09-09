package com.usuarios.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usuarios.api.dto.FornecedorDTO;
import com.usuarios.api.model.Fornecedor;
import com.usuarios.api.service.FornecedorService;

@RestController
@RequestMapping("/fornecedor")
@Secured("ROLE_ADMIN")
public class FornecedorController {
    
    @PostMapping
    public ResponseEntity<Fornecedor> cadastrarFornecedor(@RequestBody Fornecedor fornecedor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorService.salvarFornecedor(fornecedor));
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> exibirFornecedores() {
        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.listarFornecedores());
    }

    @GetMapping("/razaoSocial")
    public ResponseEntity<List<Fornecedor>> exibirFornecedoresPelaRazaoSocial(@RequestParam("razaoSocial") String razaoSocial) {
        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.listarFornecedoresRazaoSocial(razaoSocial));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorDTO> exibirClientePeloId(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.buscarFornecedorPeloId(id));
    }

    @GetMapping("/cnpj")
    public ResponseEntity<FornecedorDTO> exibirFornecedorPeloCnpj(@RequestParam("cnpj") String cnpj) {
        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.buscarFornecedorPeloCnpj(cnpj));
    }

    @GetMapping("/email")
    public ResponseEntity<FornecedorDTO> exibirFornecedorPeloEmail(@RequestParam("email") String email) {
        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.buscarFornecedorPeloEmail(email));
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Fornecedor>> exibirFornecedorPeloNome(@RequestParam("nome") String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.buscarFornecedorPeloNome(nome));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> atualizarFornecedor(@PathVariable("id") Long id, @RequestBody Fornecedor fornecedor) {
        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.atualizarDadosFornecedor(id, fornecedor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerFornecedor(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.exlcuirFornecedor(id));
    }

    
    @Autowired
    private FornecedorService fornecedorService;
}
