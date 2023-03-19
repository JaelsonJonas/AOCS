package br.com.fiap.aocs.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.aocs.models.Tarefa;
import br.com.fiap.aocs.models.Usuario;

@RestController
public class UsuarioController {

    List<Usuario> usuarios = new ArrayList<Usuario>();

    @GetMapping("/api/usuario")
    public Usuario retorna() {

        List<Tarefa> listaJow = new ArrayList<Tarefa>();
        listaJow.add(new Tarefa(69, "Estudar Java", "Estudando JPA com Hibernate", LocalDate.parse("2023-03-13"),
                LocalTime.parse("00:40")));
        listaJow.add(new Tarefa(6969, "Estudar PL/SQL", "Estudando PL/SQL com o Z", LocalDate.parse("2023-03-13"),
                LocalTime.parse("00:15")));

        return new Usuario(69, "jow@jow.com.br", "senha6969", listaJow);

    }

    @PostMapping("api/register")
    public ResponseEntity<Usuario> inserir(@RequestBody Usuario u) {
        u.setId(usuarios.size() + 1);
        usuarios.add(u);

        return ResponseEntity.status(HttpStatus.CREATED).body(u);

    }

    @PostMapping("api/login")
    public ResponseEntity<Usuario> postMethodName(@RequestBody Usuario u) {
        Optional<Usuario> usuarioConteiner = usuarios.stream()
                .filter((Usuario usuario) -> (usuario.getLogin().equals(u.getLogin()))
                        && (usuario.getSenha().equals(u.getSenha())))
                .findFirst();

        if (usuarioConteiner.isPresent())
            return ResponseEntity.ok(usuarioConteiner.get());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

}
