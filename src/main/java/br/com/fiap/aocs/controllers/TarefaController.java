package br.com.fiap.aocs.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import br.com.fiap.aocs.models.Retorno;
import br.com.fiap.aocs.models.Tarefa;
import br.com.fiap.aocs.repository.TarefaRepository;

@RestController
public class TarefaController {

    // private List<Tarefa> tarefas = new ArrayList<Tarefa>();

    @Autowired
    private TarefaRepository repository;// injetando o repositorio

    @GetMapping("/api/tarefa")
    public List<Tarefa> listAll() {

        return repository.findAll();
    }

    @PostMapping("api/tarefa")
    public ResponseEntity<Tarefa> create(@RequestBody Tarefa tarefa, UriComponentsBuilder uriCompBuilder) {

        repository.save(tarefa);
        // repository.save(tarefa);

        URI uri = uriCompBuilder.path("api/tarefa/{id}").buildAndExpand(tarefa.getId()).toUri();

        return ResponseEntity.created(uri).body(tarefa);
        // return ResponseEntity.status(HttpStatus.CREATED).body(tarefa);
    }

    @GetMapping("api/tarefa/{id}")
    public ResponseEntity<Tarefa> returnWithId(@PathVariable Integer id) {

        Optional<Tarefa> taferaConteiner = repository.findById(id);

        if (taferaConteiner.isPresent())
            return ResponseEntity.ok(taferaConteiner.get());

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("api/tarefa/{id}")
    public ResponseEntity<Retorno> deleteWithId(@PathVariable Integer id) {

        Retorno retorno = new Retorno("Tarefa removida com sucesso!");

        Optional<Tarefa> taferaConteiner = repository.findById(id);

        if (taferaConteiner.isPresent()) {
            repository.delete(taferaConteiner.get());
            return ResponseEntity.ok().body(retorno);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("api/tarefa/{id}")
    public ResponseEntity<Tarefa> updateWithId(@PathVariable Integer id, @RequestBody Tarefa update) {

        Optional<Tarefa> taferaConteiner = repository.findById(id);

        if (taferaConteiner.isPresent()) {

            update.setId(taferaConteiner.get().getId());

            repository.save(update);

            return ResponseEntity.ok().body(update);
        }

        return ResponseEntity.notFound().build();
    }
}
