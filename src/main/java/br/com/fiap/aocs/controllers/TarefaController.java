package br.com.fiap.aocs.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.aocs.models.Tarefa;

@RestController
public class TarefaController {

    private List<Tarefa> tarefas = new ArrayList<Tarefa>();

    @GetMapping("/api/tarefa")
    public List<Tarefa> listAll() {

        return tarefas;
    }

    @PostMapping("api/tarefa")
    public ResponseEntity<Tarefa> create(@RequestBody Tarefa tarefa, UriComponentsBuilder uriCompBuilder) {
        tarefa.setId(tarefas.size() + 1);
        tarefas.add(tarefa);

        URI uri = uriCompBuilder.path("api/tarefa/{id}").buildAndExpand(tarefa.getId()).toUri();

        return ResponseEntity.created(uri).body(tarefa);
        // return ResponseEntity.status(HttpStatus.CREATED).body(tarefa);
    }

    @GetMapping("api/tarefa/{id}")
    public ResponseEntity<Tarefa> returnWithId(@PathVariable Integer id) {

        Optional<Tarefa> taferaConteiner = tarefas.stream().filter((Tarefa t) -> t.getId().equals(id)).findFirst();

        if (taferaConteiner.isPresent())
            return ResponseEntity.ok(taferaConteiner.get());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}