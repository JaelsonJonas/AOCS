package br.com.fiap.aocs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.aocs.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("from Usuario u where u.login = :login")
    Optional<Usuario> findByLogin(@Param("login") String login);

}
