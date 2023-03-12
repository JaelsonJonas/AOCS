package br.com.fiap.aocs.models;

import java.util.List;

public class Usuario {

    private Integer id;
    private Login login;
    private List<Tarefa> tarefas;

    public Usuario() {

    }

    public Usuario(Integer id,Login login, List<Tarefa> tarefas) {
        this.id = id;
        this.login = login;
        this.tarefas = tarefas;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> taferas) {
        this.tarefas = taferas;
    }
}
