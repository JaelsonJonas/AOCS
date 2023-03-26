package br.com.fiap.aocs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.aocs.models.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

}