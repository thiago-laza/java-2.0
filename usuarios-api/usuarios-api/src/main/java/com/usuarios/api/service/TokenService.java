package com.usuarios.api.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.usuarios.api.model.Usuario;

@Service
public class TokenService {
    
    //Método para gerar o token de autenticação
    public String gerarToken(Usuario usuario) { 
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        return JWT.create() //Cria o token inserindo as seguintes informações
                  .withIssuer(TOKEN_ISSUER)// de onde o token está sendo gerado
                  .withSubject(usuario.getEmail())//insere o email do usuário
                  .withExpiresAt(_gerarDataExpiracao()) //insere a data de expiração do token
                  .withClaim("id", usuario.getId()) //insere o id (não é obrigtório)
                  .withClaim("nome", usuario.getNome()) //insere o nome (não é obrigtório)
                  .sign(algorithm);

    }

    //Método para a partir do token pegar o subject (usuário/email)
    public String obterEmailUsuario(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        return JWT.require(algorithm)
                  .withIssuer(TOKEN_ISSUER)
                  .build()
                  .verify(token)
                  .getSubject();
    }

    //Cria a data de expiração do token
    public Instant _gerarDataExpiracao() {
        return LocalDateTime.now().plusDays(30).toInstant(ZoneOffset.of("-03:00"));
    }

    

    @Value("${spring.security.secret-key}")
    private String SECRET_KEY;

    @Value("${spring.security.token-issuer}")
    private String TOKEN_ISSUER;

}
