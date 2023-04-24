package br.com.fiap.aocs.DTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.fiap.aocs.controllers.TarefaController;
import br.com.fiap.aocs.controllers.UsuarioController;
import br.com.fiap.aocs.models.Usuario;

public record UsuarioDTO(
        String login,
        List<TarefaDTO> tarefas) {

    public UsuarioDTO(Usuario u) {

        this(u.getLogin(), u.getTarefas().stream().map(TarefaDTO::new).toList());
    }

    public static EntityModel<UsuarioDTO> toEntityModel(Usuario u) {
        return EntityModel.of(
                new UsuarioDTO(u),
                linkTo(methodOn(UsuarioController.class).returnWithId(u.getId())).withSelfRel(),
                linkTo(methodOn(TarefaController.class).index(null, Pageable.unpaged())).withRel("all")
        );
    }

    public EntityModel<UsuarioDTO> toEntityModel(Usuario u,UsuarioDTO dto) {
        return EntityModel.of(
                this,
                linkTo(methodOn(TarefaController.class).returnWithId(u.getId())).withSelfRel(),
                linkTo(methodOn(TarefaController.class).index(null, Pageable.unpaged())).withRel("all")
        );
    }

}
