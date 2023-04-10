package br.com.fiap.aocs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.aocs.models.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    // @Query("SELECT Tarefa FROM Tarefa WHERE idUsuario = :id")
    public List<Tarefa> findByIdUsuario(Long id);
}