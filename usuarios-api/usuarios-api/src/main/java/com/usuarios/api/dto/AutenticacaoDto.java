package com.usuarios.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutenticacaoDto {
    
    @NotNull(message = "O e-mail do usuario nao pode ser nulo.")
    @NotBlank(message = "Informe o e-mail do usuario.")
    private String username;

    @NotNull(message = "A senha do usuario nao pode ser nula.")
    @NotBlank(message = "Informe a senha do usuario.")
    private String password;
}
