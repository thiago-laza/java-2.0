package com.usuarios.api.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.usuarios.api.dto.UsuarioDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity(name = "tb_usuarios")

public class Usuario implements UserDetails { //UserDetails é uma interface do Security que vai gerenciar e autorizar o usuario

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false, length = 150)
    private String nome;

    @Column (nullable = false, unique = true)
    private String email;

    @Column (nullable = false)
    private String senha;

    private String telefone;

    @Column (nullable = false)
    private String celular;

    @Column (nullable = false)
    private Boolean administrador;

    @Column (nullable = false)
    private Boolean colaborador;

    @Column (nullable = false)
    private Boolean usuarioExterno;

    // Definindo já os valores dos bolleanos
    public Usuario() {
        this.administrador = Boolean.FALSE;
        this.colaborador = Boolean.FALSE;
        this.usuarioExterno = Boolean.TRUE;
    }


    //Método converter para usuario para usuarioDto
    public UsuarioDto converterParaDto() {
        UsuarioDto dto = new UsuarioDto();

        dto.setNome(nome);
        dto.setEmail(email);
        dto.setTelefone(telefone);
        dto.setCelular(celular);
        dto.setAdministrador(administrador);
        dto.setColaborador(colaborador);
        dto.setUsuarioExterno(usuarioExterno);
        dto.setToken(null);

        return dto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //Esse método do UserDetaills dá autorização para utilizar os endpoints pré-definidos  no controller.
        if (administrador) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        if (colaborador) {
            return List.of(new SimpleGrantedAuthority("ROLE_COLAB"));
        }

        if (usuarioExterno) {
            return List.of(new SimpleGrantedAuthority("ROLE_EXT_USER"));
        }

        return null;
    }

    @Override
    public String getPassword() { //para retornar o password como senha
       return senha;
    }

    @Override
    public String getUsername() { //para retornar o username como email
       return email;
    }

    @Override
    public boolean isAccountNonExpired() { //verifica se a conta não está expirada
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  //verifica se a conta não está bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; //verifica se as credenciais não estão expiradas
    }

    @Override
    public boolean isEnabled() {
        return true; //verifica se a conta está ativa
    }
}
