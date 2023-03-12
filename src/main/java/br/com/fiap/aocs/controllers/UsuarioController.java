package br.com.fiap.aocs.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.aocs.models.Login;
import br.com.fiap.aocs.models.Tarefa;
import br.com.fiap.aocs.models.Usuario;

@RestController
public class UsuarioController {

    @GetMapping("/api/usuario")
    public Usuario retorna() {

        Login jow = new Login("jow@jow.com", "6969");
        List<Tarefa> listaJow = new ArrayList<Tarefa>();
        listaJow.add(new Tarefa(69,"Estudar Java", "Estudando JPA com Hibernate", LocalDate.parse("2023-03-13"),
        LocalTime.parse("00:40")));
        listaJow.add(new Tarefa(6969,"Estudar PL/SQL", "Estudando PL/SQL com o Z", LocalDate.parse("2023-03-13"),
        LocalTime.parse("00:15")));

        return new Usuario(69,jow,listaJow);

    }

}
