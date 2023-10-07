package br.com.fiap.aocs.models;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import jakarta.validation.constraints.NotBlank;

public record Credencial(
        @NotBlank String login,
        @NotBlank String senha) {

    public Authentication toAuthentication() {
        return new UsernamePasswordAuthenticationToken(login, senha);
    }

}
