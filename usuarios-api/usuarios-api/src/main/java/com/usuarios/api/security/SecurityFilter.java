package com.usuarios.api.security;

import java.io.IOException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.usuarios.api.service.AutenticacaoService;
import com.usuarios.api.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Essa classe verificar se o endpoint necessita de autorização e se o usuário tem acesso
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
       
                String token =_obterTokenDaRequisicao(request);

        if (Objects.nonNull(token)) {
            String email = tokenService.obterEmailUsuario(token);

            UserDetails usuario = autenticacaoService.loadUserByUsername(email);

            Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String _obterTokenDaRequisicao(HttpServletRequest requisicao){
        String authorization = requisicao.getHeader("Authorization");

        if(Objects.nonNull(authorization)) {
            return authorization.replace("Bearer ", ""); 
        }

        return null;
    }

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AutenticacaoService autenticacaoService;

    
}
