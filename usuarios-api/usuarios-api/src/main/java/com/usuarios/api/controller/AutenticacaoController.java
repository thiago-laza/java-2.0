package com.usuarios.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuarios.api.dto.AutenticacaoDto;
import com.usuarios.api.dto.UsuarioDto;
import com.usuarios.api.model.Usuario;
import com.usuarios.api.service.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
    
    //Para autenticar o usuário e gerar o token
    @PostMapping("/login")
    public ResponseEntity<UsuarioDto> login(@Valid @RequestBody AutenticacaoDto dto) {
        //Autenticação do usuário pelo próprio Spring Security
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

        //Geração do token pelo usuário retornado
        Usuario usuario = (Usuario) authentication.getPrincipal();

        String token = tokenService.gerarToken(usuario);

        //Definição do objeto a ser retornado no corpo da resposta
        UsuarioDto usuarioDto = usuario.converterParaDto();

        usuarioDto.setToken(token);

        return ResponseEntity.status(HttpStatus.OK).body(usuarioDto);
    }

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;
}
