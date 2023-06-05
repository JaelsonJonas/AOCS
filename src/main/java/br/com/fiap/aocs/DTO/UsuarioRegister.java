package br.com.fiap.aocs.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRegister(
   
@NotBlank(message = "Login ou senha invalidos")
@Size(max = 50)
@Email
String login,

@NotBlank(message = "Login ou senha invalidos")
@Size(max = 100, message = "A senha deve ter ate 100 caracteres")
String senha
) {
    
}
