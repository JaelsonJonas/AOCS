package br.com.fiap.aocs.models;

import jakarta.validation.constraints.NotBlank;

public record ValidaUsuarioDTO(
        @NotBlank String login, 
        @NotBlank String senha) {
    
}
