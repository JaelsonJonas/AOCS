package br.com.fiap.aocs.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.fiap.aocs.models.Tarefa;

public record TarefaDTO(
        String titulo,
        String descricao,
        LocalDate data,
        LocalTime duracao) {

    public TarefaDTO(Tarefa t) {
        this(t.getTitulo(), t.getDescricao(), t.getData(), t.getDuracao());
    }

}
