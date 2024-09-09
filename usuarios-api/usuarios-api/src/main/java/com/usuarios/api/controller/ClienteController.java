package com.usuarios.api.controller;

import java.time.LocalDate;
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

import com.usuarios.api.dto.ClienteDTO;
import com.usuarios.api.model.Cliente;
import com.usuarios.api.service.ClienteService;



@RestController
@RequestMapping("/clientes")
public class ClienteController {
    
    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.salvarCliente(cliente));
    }

    @Secured({"ROLE_ADMIN", "ROLE_COLAB"})
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.listarClientes());
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> exibirClientePeloId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarClientePeloId(id));
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/email")
    public ResponseEntity<ClienteDTO> exibirClientePeloEmail(@RequestParam("email") String email) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarClientePeloEmail(email));
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/nome")
    public ResponseEntity<List<Cliente>> exibirListaClientesPeloNome(@RequestParam("nome") String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.obterListaDeClientesPeloNome(nome));
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/dataInicio/dataFinal")
    public ResponseEntity<List<Cliente>> exibirListaPorDataNascimento(@RequestParam("dataInicio") LocalDate dataInicio, @RequestParam("dataFinal") LocalDate dataFinal) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.obterListaClientesPorDataNascimento(dataInicio, dataFinal));
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/cpf")
    public ResponseEntity<ClienteDTO> exibirClientePeloCpf(@RequestParam("cpf") String cpf) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.obterClientePeloCpf(cpf));
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atuazlizarDadosCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.atualizarDadosCliente(id, cliente));
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removerCliente(@PathVariable("id") Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(clienteService.excluirCliente(id));
    }



    @Autowired
    private ClienteService clienteService;
}
