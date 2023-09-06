package br.com.fiap.aocs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.aocs.DTO.TarefaDTO;
import br.com.fiap.aocs.exceptions.RestNotFoundException;
import br.com.fiap.aocs.models.ReturnAPI;
import br.com.fiap.aocs.models.Tarefa;
import br.com.fiap.aocs.repository.TarefaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/tarefa")
public class TarefaController {

    @Autowired
    private TarefaRepository repository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public Page<TarefaDTO> index(@RequestParam(required = false) String titulo,
            @PageableDefault(size = 500) Pageable pageable) {

        if (titulo == null)
            return repository.findAll(pageable)
                    .map(t -> new TarefaDTO(t.getTitulo(), t.getDescricao(), t.getData(), t.getDuracao()));

        return repository.findByTituloContaining(titulo, pageable)
                .map(t -> new TarefaDTO(t.getTitulo(), t.getDescricao(), t.getData(), t.getDuracao()));
    }

    @PostMapping
    public ResponseEntity<TarefaDTO> create(@RequestBody @Valid Tarefa tarefa) {

        repository.save(tarefa);
        
        return ResponseEntity.created(TarefaDTO.toEntityModel(tarefa).getRequiredLink("self").toUri())
                .body(new TarefaDTO(tarefa));

    }

    @GetMapping("{id}")
    public EntityModel<TarefaDTO> returnWithId(@PathVariable Long id) {

        return TarefaDTO.toEntityModel(getTarefa(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ReturnAPI> deleteWithId(@PathVariable Long id) {

        repository.delete(getTarefa(id));

        return ResponseEntity.ok(new ReturnAPI("Tarefa deletada"));

    }

    @PutMapping("{id}")
    public ResponseEntity<ReturnAPI> updateWithId(@PathVariable Long id, @RequestBody @Valid Tarefa update) {

        Tarefa tarefaUpdate = getTarefa(id);

        update.setId(tarefaUpdate.getId());

        repository.save(update);

        return ResponseEntity.ok(new ReturnAPI("Tarefa atualizada com sucesso!"));

    }

    private Tarefa getTarefa(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("Tarefa não encontrada"));
    }
}
