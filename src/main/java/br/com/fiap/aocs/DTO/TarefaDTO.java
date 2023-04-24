package br.com.fiap.aocs.DTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.fiap.aocs.controllers.TarefaController;
import br.com.fiap.aocs.controllers.UsuarioController;
import br.com.fiap.aocs.models.Tarefa;

public record TarefaDTO(
        String titulo,
        String descricao,
        LocalDate data,
        LocalTime duracao) {

    public TarefaDTO(Tarefa t) {
        this(t.getTitulo(), t.getDescricao(), t.getData(), t.getDuracao());
    }

    public static EntityModel<TarefaDTO> toEntityModel(Tarefa t) {
        return EntityModel.of(
                new TarefaDTO(t),
                linkTo(methodOn(TarefaController.class).returnWithId(t.getId())).withSelfRel(),
                linkTo(methodOn(TarefaController.class).deleteWithId(t.getId())).withRel("delete"),
                linkTo(methodOn(TarefaController.class).index(null, Pageable.unpaged())).withRel("all"),
                linkTo(methodOn(UsuarioController.class).returnWithId(t.getIdUsuario())).withRel("usuario")

        );
    }

}
