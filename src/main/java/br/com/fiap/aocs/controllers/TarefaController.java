package br.com.fiap.aocs.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.aocs.models.Tarefa;

@RestController
public class TarefaController {


    @GetMapping("/api/tarefa")
    public Tarefa retorna(){
       
         return new Tarefa("Jow","jow2","jow3");
    }

}