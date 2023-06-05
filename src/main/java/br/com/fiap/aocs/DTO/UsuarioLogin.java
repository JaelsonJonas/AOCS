package br.com.fiap.aocs.DTO;

import jakarta.validation.constraints.NotBlank;

public record UsuarioLogin(
        @NotBlank String login,
        @NotBlank String senha) {

}
