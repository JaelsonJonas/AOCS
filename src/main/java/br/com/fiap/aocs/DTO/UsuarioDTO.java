package br.com.fiap.aocs.DTO;

import java.util.List;

public record UsuarioDTO(String login, List<TarefaDTO> tarefas) {

}
