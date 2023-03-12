package br.com.fiap.aocs.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.fiap.aocs.models.Tarefa;

@RestController
public class TarefaController {

    @GetMapping("/api/tarefa")
    public Tarefa retorna() {

        return new Tarefa(69,"Estudar Java", "Estudando JPA com Hibernate", LocalDate.parse("2023-03-13"),
                LocalTime.parse("00:40"));
    }

}