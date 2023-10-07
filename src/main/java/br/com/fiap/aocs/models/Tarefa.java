package br.com.fiap.aocs.models;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.EntityModel;

import br.com.fiap.aocs.controllers.TarefaController;
import br.com.fiap.aocs.controllers.UsuarioController;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_AOCS_TAREFA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarefa {

    @Id
    @Column(name = "ID_TAREFA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome da tarefa deve possui entre 5 à 50 caracteres")
    @Size(max = 50)
    @Column(name = "NM_TAREFA", nullable = false, length = 50)
    private String titulo;

    @Size(max = 300)
    @Column(name = "DS_TAREFA", length = 300)
    private String descricao;

    @NotNull
    // @FutureOrPresent(message = "Data deve ser futura")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DT_TAREFA", nullable = false)
    private LocalDate data;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm:ss")
    @Column(name = "HR_TAREFA", nullable = false)
    private LocalTime duracao;

    @JoinColumn(name = "ID_USUARIO")
    private Long idUsuario;

    public EntityModel<Tarefa> toEntityModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(TarefaController.class).returnWithId(getId())).withSelfRel(),
                linkTo(methodOn(TarefaController.class).deleteWithId(getId())).withRel("delete"),
                linkTo(methodOn(TarefaController.class).index(null, Pageable.unpaged())).withRel("all"),
                linkTo(methodOn(UsuarioController.class).returnWithId(getIdUsuario())).withRel("usuario")

        );
    }

}