package br.com.fiap.aocs.models;

import java.util.List;

public class Usuario {

    private Integer id;
    private String login;
    private String senha;
    private List<Tarefa> tarefas;

    public Usuario() {

    }
 
    public Usuario(String login, String senha){
        this.login = login;
        this.senha = senha;
    }

    public Usuario(Integer id,String login, String senha, List<Tarefa> tarefas){
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.tarefas = tarefas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
        
    public void setSenha(String senha){
        this.senha = senha;
    }

    public String getSenha(){
        return senha;
    }

    }

}
