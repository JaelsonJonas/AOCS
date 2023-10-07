package br.com.fiap.aocs.DTO;

import jakarta.validation.constraints.NotBlank;

public record ValidaUsuarioDTO(
        @NotBlank String nome,
        @NotBlank String foto,
        @NotBlank String login,
        @NotBlank String senha) {

}
