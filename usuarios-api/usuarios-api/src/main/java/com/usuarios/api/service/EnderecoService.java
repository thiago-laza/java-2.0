package com.usuarios.api.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuarios.api.dto.EnderecoDto;

@Service
public class EnderecoService {
    
    public EnderecoDto buscarEnderecoPeloCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();

        String url = String.format("https://viacep.com.br/ws/%s/json/", cep);

        ResponseEntity<EnderecoDto> response = restTemplate.getForEntity(url, EnderecoDto.class);

        return response.getBody();
        
    }
}
