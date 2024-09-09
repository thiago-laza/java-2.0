package com.usuarios.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.usuarios.api.model.Usuario;
import com.usuarios.api.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //O username é o email do usuário (ver definição da classe usuario)
       Optional<Usuario> usuario = usuarioRepository.findByEmail(username);

       if(usuario.isPresent()) {
        return usuario.get();
       }
       return null;
    }

    //Para criptografar a senha do usuário
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UsuarioRepository usuarioRepository;

    
}
