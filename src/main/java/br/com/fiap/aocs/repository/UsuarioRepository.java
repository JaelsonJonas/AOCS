package br.com.fiap.aocs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.aocs.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByLoginAndSenha(String login, String senha);

    Optional<Usuario> findByEmail(String email);
 
}
