package br.com.fiap.aocs.DTO;

import jakarta.validation.constraints.NotBlank;

public record ValidaUsuarioDTO(
                @NotBlank String login,
                @NotBlank String senha) {

}
