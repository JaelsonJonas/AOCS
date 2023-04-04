package br.com.fiap.aocs.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.aocs.exceptions.RestNotFoundException;
import br.com.fiap.aocs.models.ReturnAPI;
import br.com.fiap.aocs.models.Tarefa;
import br.com.fiap.aocs.repository.TarefaRepository;
import jakarta.validation.Valid;

@RestController("/api/tarefa")
public class TarefaController {

    @Autowired
    private TarefaRepository repository;


    @GetMapping
    public List<Tarefa> listAll() {

        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Tarefa> create(@RequestBody @Valid Tarefa tarefa, UriComponentsBuilder uriCompBuilder) {

        repository.save(tarefa);

        URI uri = uriCompBuilder.path("api/tarefa/{id}").buildAndExpand(tarefa.getId()).toUri();

        return ResponseEntity.created(uri).body(tarefa);

    }

    @GetMapping("{id}")
    public ResponseEntity<Tarefa> returnWithId(@PathVariable Long id) {

        return ResponseEntity.ok(getTarefa(id));
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
